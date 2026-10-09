package com.pragma.payments.infrastructure.rest;

import com.pragma.payments.application.usecase.PaymentProcessingUseCase;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.PaymentStatus;
import com.pragma.payments.infrastructure.rest.dto.PaymentRequest;
import com.pragma.payments.infrastructure.rest.dto.PaymentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentProcessingUseCase paymentProcessingUseCase;

    public PaymentController(PaymentProcessingUseCase paymentProcessingUseCase) {
        this.paymentProcessingUseCase = paymentProcessingUseCase;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> processPayment(@RequestBody PaymentRequest request) {
        log.info("Recibida solicitud de pago para el usuario: {} con monto: {}", 
                request.userId(), request.amount());

        Payment payment = Payment.builder()
                .id(UUID.randomUUID().toString())
                .userId(request.userId())
                .amount(request.amount())
                .currency(request.currency() != null ? request.currency() : "USD")
                .status(PaymentStatus.PENDING)
                .createdAt(Instant.now())
                .build();

        Payment processedPayment = paymentProcessingUseCase.processPayment(payment);

        PaymentResponse response = new PaymentResponse(
                processedPayment.id(),
                processedPayment.userId(),
                processedPayment.amount(),
                processedPayment.currency(),
                processedPayment.status().name(),
                processedPayment.createdAt(),
                processedPayment.updatedAt()
        );

        HttpStatus status = determineHttpStatus(processedPayment.status());
        return ResponseEntity.status(status).body(response);
    }

    private HttpStatus determineHttpStatus(PaymentStatus status) {
        return switch (status) {
            case COMPLETED -> HttpStatus.OK;
            case PENDING -> HttpStatus.ACCEPTED;
            case FAILED -> HttpStatus.BAD_REQUEST;
            case FRAUD_REJECTED -> HttpStatus.FORBIDDEN;
            case RISK_REJECTED -> HttpStatus.PRECONDITION_FAILED;
            case BANK_REJECTED -> HttpStatus.PAYMENT_REQUIRED;
            case TIMEOUT -> HttpStatus.GATEWAY_TIMEOUT;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}