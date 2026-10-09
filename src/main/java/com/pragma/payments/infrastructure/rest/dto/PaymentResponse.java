package com.pragma.payments.infrastructure.rest.dto;


import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.PaymentStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentResponse(
    UUID paymentId,
    String externalReference,
    BigDecimal amount,
    String currency,
    PaymentStatus status,
    String fraudCheckReference,
    String riskBureauReference,
    String bankCoreReference,
    String failureReason,
    int retryCount,
    LocalDateTime createdAt,
    LocalDateTime processedAt,
    String clientId,
    String merchantId
) {
    public static PaymentResponse fromPayment(com.pragma.payments.domain.model.Payment payment) {
        return new PaymentResponse(
            payment.id(),
            payment.externalReference(),
            payment.amount(),
            payment.currency(),
            payment.status(),
            payment.fraudCheckReference().orElse(null),
            payment.riskBureauReference().orElse(null),
            payment.bankCoreReference().orElse(null),
            payment.failureReason().orElse(null),
            payment.retryCount(),
            payment.createdAt(),
            payment.processedAt().orElse(null),
            payment.clientId(),
            payment.merchantId()
        );
    }

    public static PaymentResponse pending(UUID paymentId, String externalReference, BigDecimal amount, 
                                          String currency, String clientId, String merchantId) {
        return new PaymentResponse(
            paymentId,
            externalReference,
            amount,
            currency,
            PaymentStatus.PENDING,
            null,
            null,
            null,
            null,
            0,
            LocalDateTime.now(),
            null,
            clientId,
            merchantId
        );
    }

    public boolean isSuccessful() {
        return status == PaymentStatus.APPROVED || status == PaymentStatus.COMPLETED;
    }

    public boolean isFailed() {
        return status == PaymentStatus.FAILED || status == PaymentStatus.REJECTED;
    }

    public boolean isPending() {
        return status == PaymentStatus.PENDING || status == PaymentStatus.PROCESSING;
    }

    public boolean canRetry() {
        return (status == PaymentStatus.FAILED || status == PaymentStatus.TIMEOUT) 
               && retryCount < 3;
    }

    public PaymentResponse withUpdatedStatus(PaymentStatus newStatus) {
        return new PaymentResponse(
            this.paymentId,
            this.externalReference,
            this.amount,
            this.currency,
            newStatus,
            this.fraudCheckReference,
            this.riskBureauReference,
            this.bankCoreReference,
            this.failureReason,
            this.retryCount,
            this.createdAt,
            this.processedAt,
            this.clientId,
            this.merchantId
        );
    }

    public PaymentResponse withProcessedAt(LocalDateTime processedAt) {
        return new PaymentResponse(
            this.paymentId,
            this.externalReference,
            this.amount,
            this.currency,
            this.status,
            this.fraudCheckReference,
            this.riskBureauReference,
            this.bankCoreReference,
            this.failureReason,
            this.retryCount,
            this.createdAt,
            processedAt,
            this.clientId,
            this.merchantId
        );
    }

    public PaymentResponse withFailureReason(String reason) {
        return new PaymentResponse(
            this.paymentId,
            this.externalReference,
            this.amount,
            this.currency,
            this.status,
            this.fraudCheckReference,
            this.riskBureauReference,
            this.bankCoreReference,
            reason,
            this.retryCount,
            this.createdAt,
            this.processedAt,
            this.clientId,
            this.merchantId
        );
    }
}