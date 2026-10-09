package com.pragma.payments.domain.exception;


import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.PaymentStatus;

/**
 * Excepción personalizada para errores en el procesamiento de pagos.
 * Representa errores genéricos que pueden ocurrir durante el flujo de procesamiento.
 */
public class PaymentProcessingException extends RuntimeException {

    private final String paymentId;
    private final PaymentStatus currentStatus;
    private final PaymentStatus targetStatus;
    private final String failureReason;
    private final boolean retryable;

    public PaymentProcessingException(String message) {
        super(message);
        this.paymentId = null;
        this.currentStatus = null;
        this.targetStatus = null;
        this.failureReason = message;
        this.retryable = false;
    }

    public PaymentProcessingException(String message, String paymentId) {
        super(message);
        this.paymentId = paymentId;
        this.currentStatus = null;
        this.targetStatus = null;
        this.failureReason = message;
        this.retryable = false;
    }

    public PaymentProcessingException(String message, Throwable cause) {
        super(message, cause);
        this.paymentId = null;
        this.currentStatus = null;
        this.targetStatus = null;
        this.failureReason = message;
        this.retryable = determineRetryability(cause);
    }

    public PaymentProcessingException(String message, String paymentId, 
                                       PaymentStatus currentStatus, PaymentStatus targetStatus) {
        super(message);
        this.paymentId = paymentId;
        this.currentStatus = currentStatus;
        this.targetStatus = targetStatus;
        this.failureReason = message;
        this.retryable = false;
    }

    public PaymentProcessingException(String message, Throwable cause, String paymentId,
                                       PaymentStatus currentStatus, PaymentStatus targetStatus) {
        super(message, cause);
        this.paymentId = paymentId;
        this.currentStatus = currentStatus;
        this.targetStatus = targetStatus;
        this.failureReason = message;
        this.retryable = determineRetryability(cause);
    }

    public PaymentProcessingException(String message, String paymentId, 
                                       PaymentStatus currentStatus, PaymentStatus targetStatus,
                                       String failureReason, boolean retryable) {
        super(message);
        this.paymentId = paymentId;
        this.currentStatus = currentStatus;
        this.targetStatus = targetStatus;
        this.failureReason = failureReason;
        this.retryable = retryable;
    }

    public PaymentProcessingException(String message, Throwable cause, String paymentId,
                                       PaymentStatus currentStatus, PaymentStatus targetStatus,
                                       String failureReason, boolean retryable) {
        super(message, cause);
        this.paymentId = paymentId;
        this.currentStatus = currentStatus;
        this.targetStatus = targetStatus;
        this.failureReason = failureReason;
        this.retryable = retryable;
    }

    private static boolean determineRetryability(Throwable cause) {
        if (cause == null) {
            return false;
        }
        String exceptionClassName = cause.getClass().getName();
        return exceptionClassName.contains("TimeoutException") ||
               exceptionClassName.contains("ConnectionException") ||
               exceptionClassName.contains("SocketException") ||
               exceptionClassName.contains("ServiceUnavailableException");
    }

    public String getPaymentId() {
        return paymentId;
    }

    public PaymentStatus getCurrentStatus() {
        return currentStatus;
    }

    public PaymentStatus getTargetStatus() {
        return targetStatus;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public boolean isRetryable() {
        return retryable;
    }

    public boolean hasPaymentId() {
        return paymentId != null && !paymentId.isBlank();
    }

    public boolean hasStatusInformation() {
        return currentStatus != null || targetStatus != null;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("PaymentProcessingException: ").append(super.getMessage());
        if (paymentId != null) {
            sb.append(" | PaymentId: ").append(paymentId);
        }
        if (currentStatus != null) {
            sb.append(" | CurrentStatus: ").append(currentStatus);
        }
        if (targetStatus != null) {
            sb.append(" | TargetStatus: ").append(targetStatus);
        }
        sb.append(" | Retryable: ").append(retryable);
        return sb.toString();
    }

    public String getDetailedMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Payment Processing Error ===\n");
        sb.append("Message: ").append(super.getMessage()).append("\n");
        if (paymentId != null) {
            sb.append("Payment ID: ").append(paymentId).append("\n");
        }
        if (currentStatus != null) {
            sb.append("Current Status: ").append(currentStatus).append("\n");
        }
        if (targetStatus != null) {
            sb.append("Target Status: ").append(targetStatus).append("\n");
        }
        if (failureReason != null) {
            sb.append("Failure Reason: ").append(failureReason).append("\n");
        }
        sb.append("Retryable: ").append(retryable).append("\n");
        if (getCause() != null) {
            sb.append("Caused by: ").append(getCause().getClass().getName());
            sb.append(": ").append(getCause().getMessage()).append("\n");
        }
        return sb.toString();
    }

    public static PaymentProcessingException retryable(String message, String paymentId) {
        return new PaymentProcessingException(
            message,
            (Throwable) null,
            paymentId,
            null,
            null,
            message,
            true
        );
    }

    public static PaymentProcessingException nonRetryable(String message, String paymentId) {
        return new PaymentProcessingException(
            message,
            (Throwable) null,
            paymentId,
            null,
            null,
            message,
            false
        );
    }

    public static PaymentProcessingException withStatus(String message, String paymentId,
                                                         PaymentStatus current, PaymentStatus target) {
        return new PaymentProcessingException(
            message,
            paymentId,
            current,
            target,
            message,
            false
        );
    }
}