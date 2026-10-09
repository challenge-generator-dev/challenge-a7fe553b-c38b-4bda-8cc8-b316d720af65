package com.pragma.payments.domain.exception;

public class FraudEngineException extends RuntimeException {

    private final String fraudCheckReference;
    private final String paymentId;

    public FraudEngineException(String message) {
        super(message);
        this.fraudCheckReference = null;
        this.paymentId = null;
    }

    public FraudEngineException(String message, String paymentId) {
        super(message);
        this.fraudCheckReference = null;
        this.paymentId = paymentId;
    }

    public FraudEngineException(String message, String paymentId, String fraudCheckReference) {
        super(message);
        this.paymentId = paymentId;
        this.fraudCheckReference = fraudCheckReference;
    }

    public FraudEngineException(String message, Throwable cause) {
        super(message, cause);
        this.fraudCheckReference = null;
        this.paymentId = null;
    }

    public FraudEngineException(String message, Throwable cause, String paymentId) {
        super(message, cause);
        this.paymentId = paymentId;
        this.fraudCheckReference = null;
    }

    public FraudEngineException(String message, Throwable cause, String paymentId, String fraudCheckReference) {
        super(message, cause);
        this.paymentId = paymentId;
        this.fraudCheckReference = fraudCheckReference;
    }

    public String getFraudCheckReference() {
        return fraudCheckReference;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public boolean hasFraudCheckReference() {
        return fraudCheckReference != null && !fraudCheckReference.isBlank();
    }

    public boolean hasPaymentId() {
        return paymentId != null && !paymentId.isBlank();
    }
}