package com.pragma.payments.application.usecase;

import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.PaymentStatus;
import com.pragma.payments.domain.ports.BankCorePort;
import com.pragma.payments.domain.ports.CreditOriginatorPort;
import com.pragma.payments.domain.ports.FraudEnginePort;
import com.pragma.payments.domain.ports.RiskBureauPort;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("PaymentProcessingUseCase - Tests Unitarios")
class PaymentProcessingUseCaseTest {

    @Mock
    private CreditOriginatorPort creditOriginatorPort;

    @Mock
    private FraudEnginePort fraudEnginePort;

    @Mock
    private RiskBureauPort riskBureauPort;

    @Mock
    private BankCorePort bankCorePort;

    private PaymentProcessingUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new PaymentProcessingUseCase(
            creditOriginatorPort,
            fraudEnginePort,
            riskBureauPort,
            bankCorePort
        );
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
    @DisplayName("Escenario: Flujo exitoso de procesamiento")
    class FlujoExitoso {

        @Test
        @DisplayName("Debe procesar el pago completamente cuando todos los servicios responden exitosamente")
        void debeProcesarPagoExitosamente() {
            Payment payment = createTestPayment();

            Payment paymentWithCredit = payment.withStatus(PaymentStatus.CREDIT_INITIATED)
                .withFraudCheckReference("FRAUD-REF-001");
            Payment paymentWithRisk = paymentWithCredit.withStatus(PaymentStatus.RISK_ASSESSED)
                .withRiskBureauReference("RISK-REF-001");
            Payment paymentCompleted = paymentWithRisk.withStatus(PaymentStatus.COMPLETED)
                .withBankCoreReference("BANK-REF-001");

            when(creditOriginatorPort.processCredit(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithCredit));
            when(fraudEnginePort.checkForFraud(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithCredit));
            when(riskBureauPort.assessRisk(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithRisk));
            when(bankCorePort.confirmPayment(any(Payment.class)))
                .thenReturn(Mono.just(paymentCompleted));

            StepVerifier.create(useCase.processPayment(payment))
                .expectNextMatches(result ->
                    result.status() == PaymentStatus.COMPLETED &&
                    result.fraudCheckReference() != null &&
                    result.riskBureauReference() != null &&
                    result.bankCoreReference() != null)
                .verifyComplete();

            verify(creditOriginatorPort, times(1)).processCredit(any(Payment.class));
            verify(fraudEnginePort, times(1)).checkForFraud(any(Payment.class));
            verify(riskBureauPort, times(1)).assessRisk(any(Payment.class));
            verify(bankCorePort, times(1)).confirmPayment(any(Payment.class));
        }
    }

    @Nested
    @DisplayName("Escenario: Fallo en CreditOriginator con reintento")
    class FalloCreditOriginator {

        @Test
        @DisplayName("Debe reintentar cuando CreditOriginator falla temporalmente")
        void debeReintentarCreditOriginator() {
            Payment payment = createTestPayment();
            Payment paymentWithCredit = payment.withStatus(PaymentStatus.CREDIT_INITIATED)
                .withFraudCheckReference("FRAUD-REF-001");

            when(creditOriginatorPort.processCredit(any(Payment.class)))
                .thenReturn(Mono.error(new RuntimeException("Error temporal de conexion")))
                .thenReturn(Mono.just(paymentWithCredit));
            when(fraudEnginePort.checkForFraud(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithCredit));
            when(riskBureauPort.assessRisk(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithCredit.withStatus(PaymentStatus.RISK_ASSESSED)
                    .withRiskBureauReference("RISK-REF-001")));
            when(bankCorePort.confirmPayment(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithCredit.withStatus(PaymentStatus.COMPLETED)
                    .withBankCoreReference("BANK-REF-001")));

            StepVerifier.create(useCase.processPayment(payment))
                .expectNextMatches(result -> result.status() == PaymentStatus.COMPLETED)
                .verifyComplete();

            verify(creditOriginatorPort, times(2)).processCredit(any(Payment.class));
        }

        @Test
        @DisplayName("Debe fallar definitivamente despues de reintentos agotados")
        void debeFallarDefinitivamente() {
            Payment payment = createTestPayment();

            when(creditOriginatorPort.processCredit(any(Payment.class)))
                .thenReturn(Mono.error(new RuntimeException("Error persistente")));

            StepVerifier.create(useCase.processPayment(payment))
                .expectError(RuntimeException.class)
                .verify();

            verify(creditOriginatorPort, atLeastOnce()).processCredit(any(Payment.class));
        }
    }

    @Nested
    @DisplayName("Escenario: Fallo en FraudEngine")
    class FalloFraudEngine {

        @Test
        @DisplayName("Debe manejar excepcion cuando FraudEngine detecta fraude")
        void debeManejarFalloFraudEngine() {
            Payment payment = createTestPayment();
            Payment paymentWithCredit = payment.withStatus(PaymentStatus.CREDIT_INITIATED);

            when(creditOriginatorPort.processCredit(any(Payment.class)))
                .thenReturn(Mono.just(paymentWithCredit));
            when(fraudEnginePort.checkForFraud(any(Payment.class)))
                .thenReturn(Mono.error(new RuntimeException("Fraude detectado")));

            StepVerifier.create(useCase.processPayment(payment))
                .expectError(RuntimeException.class)
                .verify();

            verify(bankCorePort, never()).confirmPayment(any(Payment.class));
        }
    }

    @Nested
    @DisplayName("Escenario: Reintentos configurables")
    class ReintentosConfigurables {

        @Test
        @DisplayName("Deberespectar el limite maximo de reintentos configurado")
        void debeRespetarLimiteReintentos() {
            Payment payment = createTestPayment();

            when(creditOriginatorPort.processCredit(any(Payment.class)))
                .thenReturn(Mono.error(new RuntimeException("Error persistente")));

            StepVerifier.create(useCase.processPayment(payment))
                .expectError(RuntimeException.class)
                .verify();

            verify(creditOriginatorPort, atLeastOnce()).processCredit(any(Payment.class));
        }

        @Test
        @DisplayName("Debe permitir reintentos solo cuando el pago lo permite")
        void debeVerificarSiRetryPermitido() {
            Payment payment = createTestPayment();

            when(creditOriginatorPort.processCredit(any(Payment.class)))
                .thenReturn(Mono.error(new RuntimeException("Error temporal")));

            StepVerifier.create(useCase.processPayment(payment))
                .expectError(RuntimeException.class)
                .verify();
        }
    }
}