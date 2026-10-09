package com.pragma.payments.domain.ports;

import com.pragma.payments.domain.model.Payment;
import reactor.core.publisher.Mono;

public interface CreditOriginatorPort {
    /**
     * Envía una solicitud de crédito al originador de créditos para su procesamiento.
     * @param payment El pago que contiene la información necesaria para procesar el crédito.
     * @return Mono que emite el pago actualizado con la referencia del originador.
     * @throws com.pragma.payments.domain.exception.PaymentProcessingException si ocurre un error durante el procesamiento.
     */
    Mono<Payment> processCredit(Payment payment);
}