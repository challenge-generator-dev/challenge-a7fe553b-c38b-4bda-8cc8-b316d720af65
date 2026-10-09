package com.pragma.payments.domain.exception;

public class RiskBureauException extends RuntimeException {

    private final String riskReference;
    private final String paymentId;
    private final String customerId;

    public RiskBureauException(String message) {
        super(message);
        this.riskReference = null;
        this.paymentId = null;
        this.customerId = null;
    }

    public RiskBureauException(String message, String paymentId) {
        super(message);
        this.riskReference = null;
        this.paymentId = paymentId;
        this.customerId = null;
    }

    public RiskBureauException(String message, String paymentId, String customerId) {
        super(message);
        this.riskReference = null;
        this.paymentId = paymentId;
        this.customerId = customerId;
    }

    public RiskBureauException(String message, String paymentId, String customerId, String riskReference) {
        super(message);
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.riskReference = riskReference;
    }

    public RiskBureauException(String message, Throwable cause) {
        super(message, cause);
        this.riskReference = null;
        this.paymentId = null;
        this.customerId = null;
    }

    public RiskBureauException(String message, Throwable cause, String paymentId) {
        super(message, cause);
        this.paymentId = paymentId;
        this.riskReference = null;
        this.customerId = null;
    }

    public RiskBureauException(String message, Throwable cause, String paymentId, String customerId) {
        super(message, cause);
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.riskReference = null;
    }

    public RiskBureauException(String message, Throwable cause, String paymentId, String customerId, String riskReference) {
        super(message, cause);
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.riskReference = riskReference;
    }

    public String getRiskReference() {
        return riskReference;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public boolean hasRiskReference() {
        return riskReference != null && !riskReference.isBlank();
    }

    public boolean hasPaymentId() {
        return paymentId != null && !paymentId.isBlank();
    }

    public boolean hasCustomerId() {
        return customerId != null && !customerId.isBlank();
    }
}