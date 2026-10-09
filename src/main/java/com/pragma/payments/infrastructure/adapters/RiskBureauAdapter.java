package com.pragma.payments.infrastructure.adapters;

import com.pragma.payments.domain.exception.RiskBureauException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.ports.RiskBureauPort;
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
public class RiskBureauAdapter implements RiskBureauPort {

    private static final Logger log = LoggerFactory.getLogger(RiskBureauAdapter.class);
    private static final Map<String, RiskCategory> RISK_CATEGORIES = new ConcurrentHashMap<>();
    
    static {
        RISK_CATEGORIES.put("LOW", RiskCategory.LOW);
        RISK_CATEGORIES.put("MEDIUM", RiskCategory.MEDIUM);
        RISK_CATEGORIES.put("HIGH", RiskCategory.HIGH);
    }

    @Override
    @CircuitBreaker(name = "riskBureauCircuitBreaker", fallbackMethod = "fallbackAssessRisk")
    @Retry(name = "riskBureauRetry")
    public Mono<Payment> assessRisk(Payment payment) {
        log.info("Iniciando evaluación de riesgo para pago: {}", payment.id());
        
        return Mono.delay(Duration.ofMillis(150))
            .flatMap(tick -> {
                RiskCategory category = calculateRiskCategory(payment);
                
                if (category == RiskCategory.HIGH) {
                    log.warn("Pago {} clasificado como alto riesgo. Categoría: {}", 
                        payment.id(), category);
                    return Mono.error(new RiskBureauException(
                        "Alto riesgo detectado para el pago: " + payment.id()));
                }
                
                String bureauReference = generateBureauReference();
                log.info("Evaluación de riesgo exitosa para pago {}. Categoría: {}, Referencia: {}", 
                    payment.id(), category, bureauReference);
                
                return Mono.just(payment.withRiskBureauReference(bureauReference));
            });
    }

    private RiskCategory calculateRiskCategory(Payment payment) {
        if (payment.amount() == null) {
            return RiskCategory.HIGH;
        }
        
        double amount = payment.amount().doubleValue();
        
        if (amount > 50000) {
            return RiskCategory.HIGH;
        } else if (amount > 10000) {
            return RiskCategory.MEDIUM;
        } else {
            return RiskCategory.LOW;
        }
    }

    private String generateBureauReference() {
        return "RB-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    @SuppressWarnings("unused")
    private Mono<Payment> fallbackAssessRisk(Payment payment, Throwable throwable) {
        log.error("Fallback activado para evaluación de riesgo. Pago: {}, Error: {}", 
            payment.id(), throwable.getMessage());
        
        if (throwable instanceof RiskBureauException) {
            return Mono.error(throwable);
        }
        
        String fallbackReference = "RB-FALLBACK-" + System.currentTimeMillis();
        return Mono.just(payment.withRiskBureauReference(fallbackReference));
    }
    
    public enum RiskCategory {
        LOW,
        MEDIUM,
        HIGH
    }
}