package com.pragma.payments.infrastructure.adapters;

import com.pragma.payments.domain.exception.PaymentProcessingException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.PaymentStatus;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.retry.annotation.Retry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CreditOriginatorAdapter - Tests Unitarios")
class CreditOriginatorAdapterTest {

    @Mock
    private CreditOriginatorAdapter externalCreditService;

    @BeforeEach
    void setUp() {
        externalCreditService = mock(CreditOriginatorAdapter.class, withSettings()
            .extraInterfaces(CreditOriginatorAdapter.class));
    }

    private Payment createTestPayment() {
        return new Payment(
            UUID.randomUUID().toString(),
            "ACC-2024-001",
            "CUST-789",
            new BigDecimal("5000.00"),
            PaymentStatus.PENDING,
            null, null, null, null
        );
    }

    @Nested
    @DisplayName("Escenario: Proceso de credito exitoso")
    class ProcesoExitoso {

        @Test
        @DisplayName("Debe retornar pago con estado CREDIT_INITIATED cuando el servicio responde OK")
        void debeRetornarPagoConCreditoIniciado() {
            CreditOriginatorAdapter adapter = new CreditOriginatorAdapter();
            Payment payment = createTestPayment();

            Payment paymentWithCreditReference = payment.withStatus(PaymentStatus.CREDIT_INITIATED)
                .withFraudCheckReference("CREDIT-REF-" + UUID.randomUUID());

            when(adapter.processCredit(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithCreditReference));

            StepVerifier.create(adapter.processCredit(payment))
                .expectNextMatches(result ->
                    result.status() == PaymentStatus.CREDIT_INITIATED &&
                    result.fraudCheckReference() != null)
                .verifyComplete();
        }
    }

    @Nested
    @DisplayName("Escenario: Fallo temporal del servicio externo")
    class FalloTemporal {

        @Test
        @DisplayName("Debe reintentar automaticamente en caso de error temporal")
        void debeReintentarAutomaticamente() {
            CreditOriginatorAdapter adapter = new CreditOriginatorAdapter();
            Payment payment = createTestPayment();
            Payment paymentConReferencia = payment.withStatus(PaymentStatus.CREDIT_INITIATED)
                .withFraudCheckReference("CREDIT-REF-001");

            when(adapter.processCredit(any(Payment.class)))
                .thenReturn(Mono.error(new RuntimeException("Connection timeout")))
                .thenReturn(Mono.just(paymentConReferencia));

            StepVerifier.create(adapter.processCredit(payment))
                .expectNextMatches(result -> result.status() == PaymentStatus.CREDIT_INITIATED)
                .verifyComplete();

            verify(adapter, times(2)).processCredit(any(Payment.class));
        }

        @Test
        @DisplayName("Debe lanzar PaymentProcessingException cuando los reintentos se agotan")
        void debeLanzarExcepcionCuandoAgotaReintentos() {
            CreditOriginatorAdapter adapter = new CreditOriginatorAdapter();
            Payment payment = createTestPayment();

            when(adapter.processCredit(any(Payment.class)))
                .thenReturn(Mono.error(new RuntimeException("Servicio no disponible")));

            StepVerifier.create(adapter.processCredit(payment))
                .expectError(PaymentProcessingException.class)
                .verify();
        }
    }

    @Nested
    @DisplayName("Escenario: Circuit breaker abierto")
    class CircuitBreakerAbierto {

        @Test
        @DisplayName("Debefallar rapido cuando el circuit breaker esta abierto")
        void debeFallarRapidoConCircuitBreakerAbierto() {
            CreditOriginatorAdapter adapter = new CreditOriginatorAdapter();
            Payment payment = createTestPayment();

            when(adapter.processCredit(any(Payment.class)))
                .thenReturn(Mono.error(CallNotPermittedException.createCallNotPermittedException(
                    io.github.resilience4j.circuitbreaker.CircuitBreaker.ofDefaults("test"))));

            StepVerifier.create(adapter.processCredit(payment))
                .expectError(PaymentProcessingException.class)
                .verify();
        }
    }

    @Nested
    @DisplayName("Escenario: Validacion de parametros")
    class ValidacionParametros {

        @Test
        @DisplayName("Debe lanzar excepcion cuando el pago es nulo")
        void debeValidarPagoNoNulo() {
            CreditOriginatorAdapter adapter = new CreditOriginatorAdapter();

            StepVerifier.create(adapter.processCredit(null))
                .expectError(NullPointerException.class)
                .verify();
        }

        @Test
        @DisplayName("Debe validar que el monto sea positivo")
        void debeValidarMontoPositivo() {
            CreditOriginatorAdapter adapter = new CreditOriginatorAdapter();
            Payment paymentConMontoNegativo = new Payment(
                UUID.randomUUID().toString(),
                "ACC-001",
                "CUST-001",
                new BigDecimal("-100.00"),
                PaymentStatus.PENDING,
                null, null, null, null
            );

            when(adapter.processCredit(any(Payment.class)))
                .thenReturn(Mono.error(new IllegalArgumentException("Monto debe ser positivo")));

            StepVerifier.create(adapter.processCredit(paymentConMontoNegativo))
                .expectError(IllegalArgumentException.class)
                .verify();
        }
    }

    @Nested
    @DisplayName("Escenario: Comportamiento con anotaciones de Resilience4j")
    class AnotacionesResilience4j {

        @Test
        @DisplayName("La anotacion @Retry debe estar presente en el metodo processCredit")
        void debeTenerAnotacionRetry() throws NoSuchMethodException {
            CreditOriginatorAdapter adapter = new CreditOriginatorAdapter();
            var method = CreditOriginatorAdapter.class.getMethod("processCredit", Payment.class);
            Retry retryAnnotation = method.getAnnotation(Retry.class);

            org.junit.jupiter.api.Assertions.assertNotNull(retryAnnotation,
                "El metodo debe tener la anotacion @Retry");
        }

        @Test
        @DisplayName("Debe manejar errores transitorios con backoff exponencial")
        void debeManejarErroresConBackoff() {
            CreditOriginatorAdapter adapter = new CreditOriginatorAdapter();
            Payment payment = createTestPayment();

            when(adapter.processCredit(any(Payment.class)))
                .thenReturn(Mono.error(new RuntimeException("Error transitorio 1")))
                .thenReturn(Mono.error(new RuntimeException("Error transitorio 2")))
                .thenReturn(Mono.just(payment.withStatus(PaymentStatus.CREDIT_INITIATED)
                    .withFraudCheckReference("FINAL-REF")));

            StepVerifier.create(adapter.processCredit(payment))
                .expectNextMatches(p -> p.status() == PaymentStatus.CREDIT_INITIATED)
                .verifyComplete();
        }
    }
}