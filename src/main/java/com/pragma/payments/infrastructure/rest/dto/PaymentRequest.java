package com.pragma.payments.infrastructure.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud de procesamiento de pago")
public record PaymentRequest(

    @Schema(description = "Identificador único del pago", example = "PAY-2024-001234")
    @NotBlank(message = "El identificador del pago es requerido")
    @Size(min = 5, max = 50, message = "El identificador debe tener entre 5 y 50 caracteres")
    String paymentId,

    @Schema(description = "Monto del pago", example = "1500.00")
    @NotNull(message = "El monto del pago es requerido")
    @Positive(message = "El monto debe ser mayor a cero")
    Double amount,

    @Schema(description = "Código de moneda ISO 4217", example = "USD")
    @NotBlank(message = "La moneda es requerida")
    @Size(min = 3, max = 3, message = "El código de moneda debe tener 3 caracteres")
    String currency,

    @Schema(description = "Identificador del cliente", example = "CUST-98765")
    @NotBlank(message = "El identificador del cliente es requerido")
    @Size(min = 5, max = 30, message = "El identificador del cliente debe tener entre 5 y 30 caracteres")
    String customerId,

    @Schema(description = "Método de pago", example = "CREDIT_CARD")
    @NotBlank(message = "El método de pago es requerido")
    String paymentMethod,

    @Schema(description = "Descripción del pago", example = "Compra en tienda en línea")
    @Size(max = 255, message = "La descripción no puede exceder 255 caracteres")
    String description,

    @Schema(description = "Número de referencia externo", example = "REF-EXT-2024-56789")
    @Size(max = 50, message = "La referencia externa no puede exceder 50 caracteres")
    String externalReference
) {
    public PaymentRequest {
        if (currency != null) {
            currency = currency.toUpperCase();
        }
    }
}