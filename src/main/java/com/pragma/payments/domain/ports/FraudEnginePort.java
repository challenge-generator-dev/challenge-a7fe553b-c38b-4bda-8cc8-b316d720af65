package com.pragma.payments.domain.ports;

import com.pragma.payments.domain.model.Payment;
import reactor.core.publisher.Mono;

public interface FraudEnginePort {
    /**
     * Realiza una verificación antifraude para el pago dado.
     * @param payment El pago que se debe verificar.
     * @return Mono que emite el pago actualizado con la referencia de la verificación antifraude.
     * @throws com.pragma.payments.domain.exception.FraudEngineException si ocurre un error durante la verificación.
     */
    Mono<Payment> checkForFraud(Payment payment);
}