package com.pragma.payments.domain.ports;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.PaymentStatus;
import reactor.core.publisher.Mono;

/**
 * Puerto para interactuar con el core bancario.
 * Define la abstracción que el dominio usa para procesar pagos a través del sistema bancario.
 * La implementación concreta se encuentra en la capa de infraestructura.
 */
public interface BankCorePort {

    /**
     * Procesa el pago a través del core bancario.
     * @param payment el pago a procesar
     * @return Mono con el pago actualizado incluyendo la referencia del core bancario
     */
    Mono<Payment> processPayment(Payment payment);

    /**
     * Consulta el estado de una transacción en el core bancario.
     * @param payment el pago con la referencia del core bancario
     * @return Mono con el estado actual del pago
     */
    Mono<PaymentStatus> queryTransactionStatus(Payment payment);

    /**
     * Reversa un pago previamente procesado.
     * @param payment el pago a reversar
     * @return Mono con el pago en estado de reversa
     */
    Mono<Payment> reversePayment(Payment payment);

    /**
     * Confirma que el pago fue completado exitosamente.
     * @param payment el pago a confirmar
     * @return Mono con el pago confirmado
     */
    Mono<Payment> confirmPayment(Payment payment);

    /**
     * Verifica la disponibilidad del core bancario.
     * @return Mono que emite true si el sistema está disponible
     */
    Mono<Boolean> isAvailable();

    /**
     * Enum para los tipos de operación bancaria.
     */
    enum BankOperationType {
        DEBIT,
        CREDIT,
        TRANSFER,
        REVERSAL,
        REFUND
    }

    /**
     * Exception específica para errores del core bancario.
     */
    class BankCoreException extends RuntimeException {
        private final String transactionReference;
        private final PaymentStatus lastStatus;
        private final BankOperationType operationType;

        public BankCoreException(String message, String transactionReference, 
                                  PaymentStatus lastStatus, BankOperationType operationType) {
            super(message);
            this.transactionReference = transactionReference;
            this.lastStatus = lastStatus;
            this.operationType = operationType;
        }

        public BankCoreException(String message, Throwable cause, String transactionReference,
                                  PaymentStatus lastStatus, BankOperationType operationType) {
            super(message, cause);
            this.transactionReference = transactionReference;
            this.lastStatus = lastStatus;
            this.operationType = operationType;
        }

        public String getTransactionReference() {
            return transactionReference;
        }

        public PaymentStatus getLastStatus() {
            return lastStatus;
        }

        public BankOperationType getOperationType() {
            return operationType;
        }

        public boolean isRetryable() {
            return this.getCause() instanceof java.io.IOException ||
                   this.getCause() instanceof java.util.concurrent.TimeoutException;
        }
    }

    /**
     * Resultado de la operación bancaria con detalles de la transacción.
     */
    record BankTransactionResult(
        String transactionReference,
        PaymentStatus status,
        BankOperationType operationType,
        long processedAt,
        String authorizationCode,
        String responseCode,
        String responseMessage
    ) {
        public boolean isSuccessful() {
            return status == PaymentStatus.APPROVED || status == PaymentStatus.COMPLETED;
        }

        public boolean requiresReversal() {
            return status == PaymentStatus.FAILED || status == PaymentStatus.REJECTED;
        }

        public static BankTransactionResult pending(String reference, BankOperationType operationType) {
            return new BankTransactionResult(
                reference,
                PaymentStatus.PENDING,
                operationType,
                System.currentTimeMillis(),
                null,
                "PENDING",
                "Transaction is being processed"
            );
        }

        public static BankTransactionResult success(String reference, BankOperationType operationType,
                                                     String authCode, String responseCode) {
            return new BankTransactionResult(
                reference,
                PaymentStatus.APPROVED,
                operationType,
                System.currentTimeMillis(),
                authCode,
                responseCode,
                "Transaction approved"
            );
        }

        public static BankTransactionResult failure(String reference, BankOperationType operationType,
                                                     String responseCode, String message) {
            return new BankTransactionResult(
                reference,
                PaymentStatus.FAILED,
                operationType,
                System.currentTimeMillis(),
                null,
                responseCode,
                message
            );
        }
    }
}