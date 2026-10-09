package com.pragma.payments.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record Payment(
    UUID id,
    @NotNull
    @Size(min = 1, max = 50)
    String accountId,
    @NotNull
    @Positive
    BigDecimal amount,
    @NotNull
    PaymentStatus status,
    @NotNull
    @Size(min = 3, max = 3)
    String currency,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    @Size(max = 255)
    String fraudCheckReference,
    @Size(max = 255)
    String riskBureauReference,
    @Size(max = 255)
    String bankCoreReference,
    int retryCount,
    LocalDateTime lastRetryAt
) {
    public Payment {
        if (id == null) {
            id = UUID.randomUUID();
        }
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (updatedAt == null) {
            updatedAt = LocalDateTime.now();
        }
        if (status == null) {
            status = PaymentStatus.PENDING;
        }
        if (retryCount < 0) {
            retryCount = 0;
        }
    }

    public Payment withStatus(PaymentStatus newStatus) {
        return new Payment(
            this.id,
            this.accountId,
            this.amount,
            newStatus,
            this.currency,
            this.createdAt,
            LocalDateTime.now(),
            this.fraudCheckReference,
            this.riskBureauReference,
            this.bankCoreReference,
            this.retryCount,
            this.lastRetryAt
        );
    }

    public Payment withRetry() {
        return new Payment(
            this.id,
            this.accountId,
            this.amount,
            this.status,
            this.currency,
            this.createdAt,
            LocalDateTime.now(),
            this.fraudCheckReference,
            this.riskBureauReference,
            this.bankCoreReference,
            this.retryCount + 1,
            LocalDateTime.now()
        );
    }

    public Payment withFraudCheckReference(String reference) {
        return new Payment(
            this.id,
            this.accountId,
            this.amount,
            this.status,
            this.currency,
            this.createdAt,
            LocalDateTime.now(),
            reference,
            this.riskBureauReference,
            this.bankCoreReference,
            this.retryCount,
            this.lastRetryAt
        );
    }

    public Payment withRiskBureauReference(String reference) {
        return new Payment(
            this.id,
            this.accountId,
            this.amount,
            this.status,
            this.currency,
            this.createdAt,
            LocalDateTime.now(),
            this.fraudCheckReference,
            reference,
            this.bankCoreReference,
            this.retryCount,
            this.lastRetryAt
        );
    }

    public Payment withBankCoreReference(String reference) {
        return new Payment(
            this.id,
            this.accountId,
            this.amount,
            this.status,
            this.currency,
            this.createdAt,
            LocalDateTime.now(),
            this.fraudCheckReference,
            this.riskBureauReference,
            reference,
            this.retryCount,
            this.lastRetryAt
        );
    }

    public boolean isRetryAllowed(int maxRetries) {
        return this.retryCount < maxRetries && this.status != PaymentStatus.COMPLETED && this.status != PaymentStatus.FAILED;
    }
}