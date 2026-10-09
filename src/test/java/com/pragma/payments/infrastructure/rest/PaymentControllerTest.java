package com.pragma.payments.infrastructure.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.PaymentStatus;
import com.pragma.payments.domain.exception.PaymentProcessingException;
import com.pragma.payments.infrastructure.rest.dto.PaymentRequest;
import com.pragma.payments.infrastructure.rest.dto.PaymentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@ExtendWith(MockitoExtension.class)
@DisplayName("PaymentController - Tests de Integracion")
class PaymentControllerTest {

    @Mock
    private PaymentController paymentController;

    private WebTestClient webTestClient;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(paymentController).build();
        objectMapper = new ObjectMapper();
    }

    private PaymentRequest createPaymentRequest() {
        PaymentRequest request = new PaymentRequest();
        request.setAccountNumber("ACC-2024-001");
        request.setCustomerId("CUST-789");
        request.setAmount(new BigDecimal("5000.00"));
        return request;
    }

    private Payment createTestPayment() {
        return new Payment(
            UUID.randomUUID().toString(),
            "ACC-2024-001",
            "CUST-789",
            new BigDecimal("5000.00"),
            PaymentStatus.COMPLETED,
            "FRAUD-REF-001", "RISK-REF-001", "BANK-REF-001", null
        );
    }

    @Nested
    @DisplayName("Escenario: Procesamiento exitoso de pago")
    class ProcesamientoExitoso {

        @Test
        @DisplayName("Debe retornar 200 OK cuando el pago se procesa correctamente")
        void debeRetornar200CuandoPagoExitoso() {
            Payment payment = createTestPayment();
            PaymentResponse response = PaymentResponse.fromDomain(payment);

            when(paymentController.processPayment(any(PaymentRequest.class)))
                .thenReturn(Mono.just(response));

            webTestClient.post()
                .uri("/api/v1/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(createPaymentRequest()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.paymentId").isEqualTo(payment.getPaymentId())
                .jsonPath("$.status").isEqualTo("COMPLETED");
        }
    }

    @Nested
    @DisplayName("Escenario: Error de validacion en request")
    class ErrorValidacion {

        @Test
        @DisplayName("Debe retornar 400 Bad Request cuando el request es invalido")
        void debeRetornar400CuandoRequestInvalido() {
            PaymentRequest requestInvalido = new PaymentRequest();
            requestInvalido.setAccountNumber("");
            requestInvalido.setCustomerId(null);
            requestInvalido.setAmount(BigDecimal.ZERO);

            webTestClient.post()
                .uri("/api/v1/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(requestInvalido))
                .exchange()
                .expectStatus().isBadRequest();
        }

        @Test
        @DisplayName("Debe validar que el monto sea mayor a cero")
        void debeValidarMontoMayorCero() {
            PaymentRequest request = createPaymentRequest();
            request.setAmount(new BigDecimal("-100.00"));

            webTestClient.post()
                .uri("/api/v1/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(request))
                .exchange()
                .expectStatus().isBadRequest();
        }
    }

    @Nested
    @DisplayName("Escenario: Error en procesamiento de pago")
    class ErrorProcesamiento {

        @Test
        @DisplayName("Debe retornar 500 Internal Server Error cuando falla el procesamiento")
        void debeRetornar500CuandoFallaProcesamiento() {
            when(paymentController.processPayment(any(PaymentRequest.class)))
                .thenReturn(Mono.error(new PaymentProcessingException("Error al procesar pago")));

            webTestClient.post()
                .uri("/api/v1/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(createPaymentRequest()))
                .exchange()
                .expectStatus().is5xxServerError();
        }

        @Test
        @DisplayName("Debe manejar error de fraude detectado correctamente")
        void debeManejarErrorFraude() {
            when(paymentController.processPayment(any(PaymentRequest.class)))
                .thenReturn(Mono.error(new PaymentProcessingException("Fraude detectado")));

            webTestClient.post()
                .uri("/api/v1/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(createPaymentRequest()))
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody()
                .jsonPath("$.error").exists();
        }
    }

    @Nested
    @DisplayName("Escenario: Endpoints adicionales del controlador")
    class EndpointsAdicionales {

        @Test
        @DisplayName("Debe obtener estado del pago por ID")
        void debeObtenerPagoPorId() {
            Payment payment = createTestPayment();
            PaymentResponse response = PaymentResponse.fromDomain(payment);

            when(paymentController.getPaymentById(anyString()))
                .thenReturn(Mono.just(response));

            webTestClient.get()
                .uri("/api/v1/payments/{id}", payment.getPaymentId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.paymentId").isEqualTo(payment.getPaymentId());
        }

        @Test
        @DisplayName("Debe retornar 404 cuando el pago no existe")
        void debeRetornar404CuandoPagoNoExiste() {
            String paymentIdInexistente = "PAY-999";

            when(paymentController.getPaymentById(paymentIdInexistente))
                .thenReturn(Mono.empty());

            webTestClient.get()
                .uri("/api/v1/payments/{id}", paymentIdInexistente)
                .exchange()
                .expectStatus().isNotFound();
        }

        @Test
        @DisplayName("Debe listar pagos por estado")
        void debeListarPagosPorEstado() {
            Payment payment = createTestPayment();
            PaymentResponse response = PaymentResponse.fromDomain(payment);

            when(paymentController.getPaymentsByStatus(anyString()))
                .thenReturn(Mono.just(java.util.List.of(response)));

            webTestClient.get()
                .uri("/api/v1/payments?status=COMPLETED")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].status").isEqualTo("COMPLETED");
        }
    }

    @Nested
    @DisplayName("Escenario: Headers y content negotiation")
    class HeadersYContentNegotiation {

        @Test
        @DisplayName("Debe aceptar solo JSON como content type")
        void debeAceptarSoloJson() {
            webTestClient.post()
                .uri("/api/v1/payments")
                .contentType(MediaType.APPLICATION_XML)
                .body(fromValue("<payment/>"))
                .exchange()
                .expectStatus().isUnsupportedMediaType();
        }

        @Test
n        @DisplayName("Debe incluir header X-Request-ID en la respuesta")
        void debeIncluirRequestIdEnRespuesta() {
            Payment payment = createTestPayment();
            PaymentResponse response = PaymentResponse.fromDomain(payment);

            when(paymentController.processPayment(any(PaymentRequest.class)))
                .thenReturn(Mono.just(response));

            webTestClient.post()
                .uri("/api/v1/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-Request-ID", "REQ-12345")
                .body(fromValue(createPaymentRequest()))
                .exchange()
                .expectHeader().exists("X-Request-ID");
        }
    }
}