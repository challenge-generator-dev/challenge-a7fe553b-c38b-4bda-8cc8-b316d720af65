package com.pragma.payments.infrastructure.adapters;

import com.pragma.payments.domain.exception.FraudEngineException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.ports.FraudEnginePort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@Component
public class FraudEngineAdapter implements FraudEnginePort {

    private static final Logger log = LoggerFactory.getLogger(FraudEngineAdapter.class);
    private static final double DEFAULT_FRAUD_THRESHOLD = 0.75;

    @Override
    @CircuitBreaker(name = "fraudEngineCircuitBreaker", fallbackMethod = "fallbackCheckForFraud")
    @Retry(name = "fraudEngineRetry")
    public Mono<Payment> checkForFraud(Payment payment) {
        log.info("Iniciando verificación antifraude para pago: {}", payment.id());
        
        return Mono.delay(Duration.ofMillis(100))
            .flatMap(tick -> {
                double riskScore = calculateRiskScore(payment);
                
                if (riskScore > DEFAULT_FRAUD_THRESHOLD) {
                    log.warn("Pago {} marcado como fraudulento. Score de riesgo: {}", 
                        payment.id(), riskScore);
                    return Mono.error(new FraudEngineException(
                        "Riesgo de fraude detectado para el pago: " + payment.id()));
                }
                
                String fraudReference = generateFraudReference();
                log.info("Verificación antifraude exitosa para pago {}. Referencia: {}", 
                    payment.id(), fraudReference);
                
                return Mono.just(payment.withFraudCheckReference(fraudReference));
            });
    }

    private double calculateRiskScore(Payment payment) {
        if (payment.amount() == null || payment.amount().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            return 1.0;
        }
        
        double amountFactor = payment.amount().doubleValue() > 10000 ? 0.4 : 0.1;
        double currencyFactor = payment.currency() != null && !"USD".equals(payment.currency()) 
            ? 0.2 : 0.0;
        double baseScore = 0.3;
        
        return Math.min(1.0, baseScore + amountFactor + currencyFactor);
    }

    private String generateFraudReference() {
        return "FE-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    @SuppressWarnings("unused")
    private Mono<Payment> fallbackCheckForFraud(Payment payment, Throwable throwable) {
        log.error("Fallback activado para verificación antifraude. Pago: {}, Error: {}", 
            payment.id(), throwable.getMessage());
        
        if (throwable instanceof FraudEngineException) {
            return Mono.error(throwable);
        }
        
        String fallbackReference = "FE-FALLBACK-" + System.currentTimeMillis();
        return Mono.just(payment.withFraudCheckReference(fallbackReference));
    }
}