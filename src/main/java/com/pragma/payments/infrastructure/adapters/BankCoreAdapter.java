package com.pragma.payments.infrastructure.adapters;

import com.pragma.payments.domain.exception.PaymentProcessingException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.ports.BankCorePort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@Component
public class BankCoreAdapter implements BankCorePort {

    private static final Logger log = LoggerFactory.getLogger(BankCoreAdapter.class);
    private static final String TRANSACTION_STATUS_APPROVED = "APPROVED";
    private static final String TRANSACTION_STATUS_REJECTED = "REJECTED";
    private static final String TRANSACTION_STATUS_PENDING = "PENDING";

    @Override
    @CircuitBreaker(name = "bankCoreCircuitBreaker", fallbackMethod = "fallbackProcessPayment")
    @Retry(name = "bankCoreRetry")
    public Mono<Payment> processPayment(Payment payment) {
        log.info("Iniciando procesamiento de pago en core bancario: {}", payment.id());
        
        return Mono.delay(Duration.ofMillis(200))
            .flatMap(tick -> {
                boolean isApproved = evaluateTransaction(payment);
                
                if (!isApproved) {
                    log.warn("Transacción rechazada por el core bancario para pago: {}", payment.id());
                    return Mono.error(new PaymentProcessingException(
                        "Transacción rechazada por el core bancario: " + payment.id()));
                }
                
                String bankReference = generateBankReference();
                log.info("Pago {} procesado exitosamente en core bancario. Referencia: {}", 
                    payment.id(), bankReference);
                
                return Mono.just(payment.withBankCoreReference(bankReference));
            });
    }

    private boolean evaluateTransaction(Payment payment) {
        if (payment.amount() == null || payment.amount().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            return false;
        }
        
        double amount = payment.amount().doubleValue();
        
        if (amount > 100000) {
            return false;
        }
        
        return true;
    }

    private String generateBankReference() {
        return "TXN-" + UUID.randomUUID().toString().substring(0, 12).toUpperCase();
    }

    @SuppressWarnings("unused")
    private Mono<Payment> fallbackProcessPayment(Payment payment, Throwable throwable) {
        log.error("Fallback activado para procesamiento en core bancario. Pago: {}, Error: {}", 
            payment.id(), throwable.getMessage());
        
        if (throwable instanceof PaymentProcessingException) {
            return Mono.error(throwable);
        }
        
        String fallbackReference = "TXN-FALLBACK-" + System.currentTimeMillis();
        return Mono.just(payment.withBankCoreReference(fallbackReference));
    }
}