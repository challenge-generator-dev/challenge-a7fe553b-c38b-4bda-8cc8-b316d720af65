package com.pragma.payments.application.usecase;

import com.pragma.payments.domain.exception.PaymentProcessingException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.PaymentStatus;
import com.pragma.payments.domain.ports.BankCorePort;
import com.pragma.payments.domain.ports.CreditOriginatorPort;
import com.pragma.payments.domain.ports.FraudEnginePort;
import com.pragma.payments.domain.ports.RiskBureauPort;
import com.pragma.payments.infrastructure.rest.dto.PaymentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class PaymentProcessingUseCase {

    private static final Logger log = LoggerFactory.getLogger(PaymentProcessingUseCase.class);
    private static final int MAX_RETRIES = 3;
    private static final Duration RETRY_BACKOFF = Duration.ofSeconds(2);

    private final CreditOriginatorPort creditOriginatorPort;
    private final FraudEnginePort fraudEnginePort;
    private final RiskBureauPort riskBureauPort;
    private final BankCorePort bankCorePort;

    public PaymentProcessingUseCase(
            CreditOriginatorPort creditOriginatorPort,
            FraudEnginePort fraudEnginePort,
            RiskBureauPort riskBureauPort,
            BankCorePort bankCorePort) {
        this.creditOriginatorPort = creditOriginatorPort;
        this.fraudEnginePort = fraudEnginePort;
        this.riskBureauPort = riskBureauPort;
        this.bankCorePort = bankCorePort;
    }

    public Mono<PaymentResponse> processPayment(Payment payment) {
        log.info("Iniciando procesamiento de pago: {} con monto {} {}", 
                payment.id(), payment.amount(), payment.currency());

        return executePaymentPipeline(payment)
            .doOnSuccess(result -> log.info("Pago {} procesado exitosamente con estado: {}", 
                    result.paymentId(), result.status()))
            .doOnError(error -> log.error("Error en procesamiento de pago {}: {}", 
                    payment.id(), error.getMessage(), error))
            .onErrorResume(error -> handlePaymentError(payment, error));
    }

    private Mono<PaymentResponse> executePaymentPipeline(Payment payment) {
        return creditOriginatorPort.processCredit(payment)
            .flatMap(this::validateWithFraudEngine)
            .flatMap(this::enrichWithRiskBureau)
            .flatMap(this::authorizeWithBankCore)
            .map(PaymentResponse::fromPayment)
            .timeout(Duration.ofSeconds(2), Mono.error(
                new PaymentProcessingException("Timeout en procesamiento de pago", "TIMEOUT")));
    }

    private Mono<Payment> validateWithFraudEngine(Payment payment) {
        log.debug("Validando pago {} con motor antifraude", payment.id());
        return fraudEnginePort.checkForFraud(payment)
            .doOnNext(result -> log.debug("Resultado de antifraude para {}: {}", 
                    result.id(), result.status()));
    }

    private Mono<Payment> enrichWithRiskBureau(Payment payment) {
        log.debug("Enriqueciendo pago {} con datos del buró de riesgos", payment.id());
        return riskBureauPort.enrichWithRiskData(payment)
            .doOnNext(result -> log.debug("Datos de riesgo obtenidos para {}", result.id()))
            .onErrorResume(error -> {
                log.warn("Error al obtener datos de riesgo para {}, continuando: {}", 
                        payment.id(), error.getMessage());
                return Mono.just(payment);
            });
    }

    private Mono<Payment> authorizeWithBankCore(Payment payment) {
        log.debug("Autorizando pago {} con core bancario", payment.id());
        return bankCorePort.authorizePayment(payment)
            .doOnNext(result -> log.debug("Autorización bancaria para {}: {}", 
                    result.id(), result.status()));
    }

    private Mono<PaymentResponse> handlePaymentError(Payment payment, Throwable error) {
        String errorCode = extractErrorCode(error);
        String errorMessage = error.getMessage();

        log.warn("Manejando error para pago {}: código={}, mensaje={}", 
                payment.id(), errorCode, errorMessage);

        if (payment.isRetryAllowed(MAX_RETRIES)) {
            log.info("Pago {} elegible para reintento. Retry count: {}", 
                    payment.id(), payment.retryCount() + 1);
            return retryPayment(payment, errorCode, errorMessage);
        }

        log.error("Pago {} no elegible para más reintentos. Marcando como fallido.", payment.id());
        Payment failedPayment = payment
            .withStatus(PaymentStatus.FAILED)
            .withFailureReason(errorMessage);
        return Mono.just(PaymentResponse.fromPayment(failedPayment));
    }

    private Mono<PaymentResponse> retryPayment(Payment payment, String errorCode, String errorMessage) {
        Payment retryPayment = payment.withRetry();

        return Mono.delay(RETRY_BACKOFF)
            .flatMap(tick -> {
                log.info("Ejecutando reintento {} para pago {}", 
                        retryPayment.retryCount(), retryPayment.id());
                return executePaymentPipeline(retryPayment);
            })
            .retryWhen(Retry.backoff(MAX_RETRIES, RETRY_BACKOFF)
                .filter(throwable -> isRetryableError(throwable))
                .doBeforeRetry(retrySignal -> {
                    log.warn("Reintento {} para pago {} después de error: {}",
                            retrySignal.totalRetries() + 1,
                            payment.id(),
                            retrySignal.failure().getMessage());
                })
                .onRetryExhaustedThrow((spec, signal) -> {
                    log.error("Reintentos agotados para pago {}", payment.id());
                    return new PaymentProcessingException(
                        "Reintentos agotados para el pago", "RETRY_EXHAUSTED");
                }))
            .onErrorResume(error -> {
                log.error("Todos los reintentos fallaron para pago {}", payment.id());
                Payment failedPayment = payment
                    .withStatus(PaymentStatus.FAILED)
                    .withFailureReason("Reintentos agotados: " + error.getMessage());
                return Mono.just(PaymentResponse.fromPayment(failedPayment));
            });
    }

    private boolean isRetryableError(Throwable error) {
        String errorCode = extractErrorCode(error);
        return "TIMEOUT".equals(errorCode) 
               || "CONNECTION_ERROR".equals(errorCode)
               || "SERVICE_UNAVAILABLE".equals(errorCode)
               || "RATE_LIMIT_EXCEEDED".equals(errorCode);
    }

    private String extractErrorCode(Throwable error) {
        if (error instanceof PaymentProcessingException paymentError) {
            return paymentError.getErrorCode();
        }
        return "UNKNOWN_ERROR";
    }

    public Mono<PaymentResponse> getPaymentStatus(UUID paymentId) {
        log.info("Consultando estado del pago: {}", paymentId);
        return creditOriginatorPort.findById(paymentId)
            .map(PaymentResponse::fromPayment)
            .switchIfEmpty(Mono.error(new PaymentProcessingException(
                "Pago no encontrado: " + paymentId, "NOT_FOUND")));
    }

    public Mono<PaymentResponse> cancelPayment(UUID paymentId) {
        log.info("Cancelando pago: {}", paymentId);
        return creditOriginatorPort.findById(paymentId)
            .flatMap(payment -> {
                if (payment.status() == PaymentStatus.COMPLETED || 
                    payment.status() == PaymentStatus.APPROVED) {
                    return Mono.error(new PaymentProcessingException(
                        "No se puede cancelar un pago ya aprobado", "CANNOT_CANCEL"));
                }
                Payment cancelledPayment = payment.withStatus(PaymentStatus.CANCELLED);
                return creditOriginatorPort.processCredit(cancelledPayment);
            })
            .map(PaymentResponse::fromPayment);
    }
}