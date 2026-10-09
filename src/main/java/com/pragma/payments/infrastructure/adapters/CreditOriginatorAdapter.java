package com.pragma.payments.infrastructure.adapters;


import com.pragma.payments.domain.model.PaymentStatus;
import com.pragma.payments.domain.exception.PaymentProcessingException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.ports.CreditOriginatorPort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CreditOriginatorAdapter implements CreditOriginatorPort {

    private static final Logger log = LoggerFactory.getLogger(CreditOriginatorAdapter.class);
    private static final Map<UUID, Payment> PAYMENT_STORE = new ConcurrentHashMap<>();

    @Override
    @CircuitBreaker(name = "creditOriginator", fallbackMethod = "processCreditFallback")
    @Retry(name = "creditOriginatorRetry")
    public Mono<Payment> processCredit(Payment payment) {
        log.info("Procesando crédito para pago: {} - Monto: {} {}",
                payment.id(), payment.amount(), payment.currency());

        return Mono.fromCallable(() -> {
            simulateCreditProcessing(payment);
            Payment processedPayment = payment.withStatus(determinePaymentStatus(payment));
            PAYMENT_STORE.put(processedPayment.id(), processedPayment);
            log.info("Crédito procesado exitosamente para pago: {}", processedPayment.id());
            return processedPayment;
        }).delayElement(Duration.ofMillis(100));
    }

    private void simulateCreditProcessing(Payment payment) throws InterruptedException {
        if (payment.externalReference().startsWith("ERROR")) {
            throw new PaymentProcessingException(
                "Error simulado en originador de crédito", "CREDIT_PROCESSING_ERROR");
        }
        if (payment.externalReference().startsWith("TIMEOUT")) {
            throw new PaymentProcessingException(
                "Timeout simulado en originador de crédito", "TIMEOUT");
        }
        if (payment.externalReference().startsWith("UNAVAILABLE")) {
            throw new PaymentProcessingException(
                "Servicio no disponible", "SERVICE_UNAVAILABLE");
        }
    }

    private com.pragma.payments.domain.model.PaymentStatus determinePaymentStatus(Payment payment) {
        if (payment.amount().compareTo(payment.maximumAllowedAmount()) > 0) {
            return com.pragma.payments.domain.model.PaymentStatus.REJECTED;
        }
        if (payment.clientCreditScore() < 500) {
            return com.pragma.payments.domain.model.PaymentStatus.REJECTED;
        }
        return com.pragma.payments.domain.model.PaymentStatus.APPROVED;
    }

    @SuppressWarnings("unused")
    private Mono<Payment> processCreditFallback(Payment payment, Throwable error) {
        log.warn("Fallback activado para pago {}: {}", payment.id(), error.getMessage());
        
        if (error instanceof PaymentProcessingException paymentError) {
            String errorCode = paymentError.getErrorCode();
            
            if ("TIMEOUT".equals(errorCode)) {
                return Mono.just(payment.withStatus(
                    com.pragma.payments.domain.model.PaymentStatus.TIMEOUT));
            }
            if ("SERVICE_UNAVAILABLE".equals(errorCode)) {
                return Mono.just(payment.withStatus(
                    com.pragma.payments.domain.model.PaymentStatus.PENDING))
                    .delayElement(Duration.ofSeconds(5))
                    .flatMap(this::processCredit);
            }
        }
        
        return Mono.just(payment.withStatus(
            com.pragma.payments.domain.model.PaymentStatus.FAILED)
            .withFailureReason("Error en originador de crédito: " + error.getMessage()));
    }

    @Override
    public Mono<Payment> findById(UUID paymentId) {
        log.debug("Buscando pago por ID: {}", paymentId);
        return Mono.fromCallable(() -> {
            Payment payment = PAYMENT_STORE.get(paymentId);
            if (payment == null) {
                throw new PaymentProcessingException(
                    "Pago no encontrado: " + paymentId, "NOT_FOUND");
            }
            return payment;
        });
    }

    @Override
    public Mono<Payment> updatePaymentStatus(UUID paymentId, 
            com.pragma.payments.domain.model.PaymentStatus newStatus) {
        log.info("Actualizando estado del pago {} a {}", paymentId, newStatus);
        return findById(paymentId)
            .map(payment -> {
                Payment updatedPayment = payment.withStatus(newStatus)
                    .withProcessedAt(java.time.LocalDateTime.now());
                PAYMENT_STORE.put(paymentId, updatedPayment);
                return updatedPayment;
            });
    }

    public Mono<Boolean> validateCreditLimit(String clientId, java.math.BigDecimal amount) {
        log.debug("Validando límite de crédito para cliente {} con monto {}", clientId, amount);
        return Mono.fromCallable(() -> {
            int clientHash = clientId.hashCode();
            java.math.BigDecimal limit = java.math.BigDecimal.valueOf(Math.abs(clientHash % 100000) + 10000);
            return amount.compareTo(limit) <= 0;
        });
    }

    public Mono<java.math.BigDecimal> getAvailableCredit(String clientId) {
        log.debug("Consultando crédito disponible para cliente: {}", clientId);
        return Mono.fromCallable(() -> {
            int clientHash = clientId.hashCode();
            return java.math.BigDecimal.valueOf(Math.abs(clientHash % 100000) + 5000);
        });
    }
}