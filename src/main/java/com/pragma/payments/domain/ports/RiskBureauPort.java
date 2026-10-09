package com.pragma.payments.domain.ports;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.PaymentStatus;
import reactor.core.publisher.Mono;

/**
 * Puerto para interactuar con el buró de riesgos.
 * Define la abstracción que el dominio usa para consultar información de riesgo crediticio.
 * La implementación concreta se encuentra en la capa de infraestructura.
 */
public interface RiskBureauPort {

    /**
     * Consulta el buró de riesgos para obtener la calificación de riesgo del cliente.
     * @param payment el pago con información del cliente a evaluar
     * @return Mono con el pago actualizado incluyendo la referencia del buró de riesgos
     */
    Mono<Payment> consultRiskBureau(Payment payment);

    /**
     * Verifica si el cliente cumple con los requisitos mínimos de riesgo.
     * @param payment el pago a verificar
     * @return Mono que emite true si el riesgo es aceptable, false en caso contrario
     */
    Mono<Boolean> isRiskAcceptable(Payment payment);

    /**
     * Obtiene el puntaje de riesgo del cliente desde el buró.
     * @param payment el pago con información del cliente
     * @return Mono con el puntaje de riesgo (0-1000)
     */
    Mono<Integer> getRiskScore(Payment payment);

    /**
     * Enum interno para categorizar el nivel de riesgo.
     */
    enum RiskLevel {
        LOW(0, 300),
        MEDIUM(301, 600),
        HIGH(601, 800),
        VERY_HIGH(801, 1000);

        private final int minScore;
        private final int maxScore;

        RiskLevel(int minScore, int maxScore) {
            this.minScore = minScore;
            this.maxScore = maxScore;
        }

        public boolean containsScore(int score) {
            return score >= minScore && score <= maxScore;
        }

        public static RiskLevel fromScore(int score) {
            for (RiskLevel level : values()) {
                if (level.containsScore(score)) {
                    return level;
                }
            }
            return VERY_HIGH;
        }
    }

    /**
     * Exception específica para errores del buró de riesgos.
     */
    class RiskBureauException extends RuntimeException {
        private final String customerId;
        private final PaymentStatus lastStatus;

        public RiskBureauException(String message, String customerId, PaymentStatus lastStatus) {
            super(message);
            this.customerId = customerId;
            this.lastStatus = lastStatus;
        }

        public RiskBureauException(String message, Throwable cause, String customerId, PaymentStatus lastStatus) {
            super(message, cause);
            this.customerId = customerId;
            this.lastStatus = lastStatus;
        }

        public String getCustomerId() {
            return customerId;
        }

        public PaymentStatus getLastStatus() {
            return lastStatus;
        }
    }

    /**
     * Resultado de la consulta de riesgo con detalles adicionales.
     */
    record RiskConsultationResult(
        String customerId,
        int riskScore,
        RiskLevel riskLevel,
        boolean isAcceptable,
        String bureauReference,
        long consultationTimestamp
    ) {
        public static RiskConsultationResult create(String customerId, int score, String reference) {
            RiskLevel level = RiskLevel.fromScore(score);
            return new RiskConsultationResult(
                customerId,
                score,
                level,
                level == RiskLevel.LOW || level == RiskLevel.MEDIUM,
                reference,
                System.currentTimeMillis()
            );
        }

        public boolean requiresManualApproval() {
            return riskLevel == RiskLevel.HIGH || riskLevel == RiskLevel.VERY_HIGH;
        }
    }
}