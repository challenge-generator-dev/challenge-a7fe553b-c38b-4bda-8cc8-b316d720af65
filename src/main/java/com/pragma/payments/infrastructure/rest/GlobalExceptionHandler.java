package com.pragma.payments.infrastructure.rest;

import com.pragma.payments.domain.exception.FraudEngineException;
import com.pragma.payments.domain.exception.PaymentProcessingException;
import com.pragma.payments.domain.exception.RiskBureauException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(PaymentProcessingException.class)
    public ResponseEntity<Map<String, Object>> handlePaymentProcessingException(PaymentProcessingException ex) {
        log.error("Error procesando pago: {}", ex.getMessage(), ex);
        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "PAYMENT_PROCESSING_ERROR",
                ex.getMessage(),
                ex.getErrorCode()
        );
    }

    @ExceptionHandler(FraudEngineException.class)
    public ResponseEntity<Map<String, Object>> handleFraudEngineException(FraudEngineException ex) {
        log.error("Error en motor antifraude: {}", ex.getMessage(), ex);
        return buildErrorResponse(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "FRAUD_ENGINE_ERROR",
                ex.getMessage(),
                ex.getErrorCode()
        );
    }

    @ExceptionHandler(RiskBureauException.class)
    public ResponseEntity<Map<String, Object>> handleRiskBureauException(RiskBureauException ex) {
        log.error("Error en bureau de riesgos: {}", ex.getMessage(), ex);
        return buildErrorResponse(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "RISK_BUREAU_ERROR",
                ex.getMessage(),
                ex.getErrorCode()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        log.warn("Error de validación en request: {}", ex.getMessage());
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
                fieldErrors.put(error.getField(), error.getDefaultMessage())
        );
        
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", Instant.now().toString());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "VALIDATION_ERROR");
        response.put("message", "Los datos del pago no son válidos");
        response.put("details", fieldErrors);
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.warn("Argumento inválido: {}", ex.getMessage());
        return buildErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "INVALID_ARGUMENT",
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        log.error("Error inesperado del sistema: {}", ex.getMessage(), ex);
        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_SYSTEM_ERROR",
                "Ha ocurrido un error inesperado. Por favor contacte al administrador.",
                null
        );
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(int status, String error, String message, String errorCode) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", Instant.now().toString());
        response.put("status", status);
        response.put("error", error);
        response.put("message", message);
        if (errorCode != null) {
            response.put("errorCode", errorCode);
        }
        return ResponseEntity.status(status).body(response);
    }
}