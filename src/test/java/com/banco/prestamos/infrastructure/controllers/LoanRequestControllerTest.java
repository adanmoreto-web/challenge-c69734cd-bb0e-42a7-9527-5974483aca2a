package com.banco.prestamos.infrastructure.controllers;

import com.banco.prestamos.application.usecases.CreateLoanRequestUseCase;
import com.banco.prestamos.domain.model.LoanRequest;
import com.banco.prestamos.domain.model.LoanRequest.LoanStatus;
import com.banco.prestamos.infrastructure.exceptions.GlobalExceptionHandler;
import com.banco.prestamos.infrastructure.exceptions.LoanRequestAlreadyExistsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests de integracion para LoanRequestController")
class LoanRequestControllerTest {

    @Mock
    private CreateLoanRequestUseCase createLoanRequestUseCase;

    @InjectMocks
    private LoanRequestController loanRequestController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(loanRequestController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Debe retornar 201 Created cuando la solicitud se crea exitosamente")
    void createLoanRequest_DeberiaRetornar201_CuandoExitoso() throws Exception {
        Long customerId = 54321L;
        BigDecimal amount = new BigDecimal("75000.00");
        Integer termMonths = 30;
        String idempotencyKey = "key-create-success";
        UUID requestId = UUID.randomUUID();

        LoanRequest loanRequest = new LoanRequest(
            requestId,
            customerId,
            amount,
            termMonths,
            LoanStatus.PENDING,
            LocalDate.now(),
            null,
            idempotencyKey
        );

        when(createLoanRequestUseCase.execute(eq(customerId), eq(amount), eq(termMonths), eq(idempotencyKey)))
            .thenReturn(loanRequest);

        Map<String, Object> requestBody = Map.of(
            "customerId", customerId,
            "amount", amount,
            "termMonths", termMonths,
            "idempotencyKey", idempotencyKey
        );

        mockMvc.perform(post("/api/v1/loan-requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "/api/v1/loan-requests/" + requestId))
            .andExpect(jsonPath("$.requestId").value(requestId.toString()))
            .andExpect(jsonPath("$.customerId").value(customerId))
            .andExpect(jsonPath("$.status").value("PENDING"));

        verify(createLoanRequestUseCase).execute(customerId, amount, termMonths, idempotencyKey);
    }

    @Test
    @DisplayName("Debe retornar 400 Bad Request cuando el monto es invalido")
    void createLoanRequest_DeberiaRetornar400_CuandoMontoInvalido() throws Exception {
        Long customerId = 54321L;
        BigDecimal invalidAmount = BigDecimal.ZERO;
        Integer termMonths = 30;
        String idempotencyKey = "key-invalid-amount";

        when(createLoanRequestUseCase.execute(eq(customerId), eq(invalidAmount), eq(termMonths), eq(idempotencyKey)))
            .thenThrow(new IllegalArgumentException("El monto debe ser mayor a cero"));

        Map<String, Object> requestBody = Map.of(
            "customerId", customerId,
            "amount", invalidAmount,
            "termMonths", termMonths,
            "idempotencyKey", idempotencyKey
        );

        mockMvc.perform(post("/api/v1/loan-requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").exists())
            .andExpect(jsonPath("$.message").value("El monto debe ser mayor a cero"));
    }

    @Test
    @DisplayName("Debe retornar 409 Conflict cuando la solicitud ya existe (idempotencia)")
    void createLoanRequest_DeberiaRetornar409_CuandoSolicitudDuplicada() throws Exception {
        Long customerId = 99999L;
        BigDecimal amount = new BigDecimal("100000.00");
        Integer termMonths = 48;
        String idempotencyKey = "key-duplicate";
        UUID existingRequestId = UUID.randomUUID();

        when(createLoanRequestUseCase.execute(eq(customerId), eq(amount), eq(termMonths), eq(idempotencyKey)))
            .thenThrow(new LoanRequestAlreadyExistsException(customerId, existingRequestId, idempotencyKey));

        Map<String, Object> requestBody = Map.of(
            "customerId", customerId,
            "amount", amount,
            "termMonths", termMonths,
            "idempotencyKey", idempotencyKey
        );

        mockMvc.perform(post("/api/v1/loan-requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
            .andExpect(status().isConflict())
            .andExpect(jsonPath("$.error").value("LOAN_REQUEST_DUPLICATE"))
            .andExpect(jsonPath("$.message").exists());
    }

    @Test
    @DisplayName("Debe retornar 400 Bad Request cuando falta el campo customerId")
    void createLoanRequest_DeberiaRetornar400_CuandoFaltaCustomerId() throws Exception {
        Map<String, Object> requestBody = Map.of(
            "amount", new BigDecimal("50000.00"),
            "termMonths", 24,
            "idempotencyKey", "key-missing-customer"
        );

        mockMvc.perform(post("/api/v1/loan-requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Debe retornar 400 Bad Request cuando el JSON esta mal formado")
    void createLoanRequest_DeberiaRetornar400_CuandoJsonInvalido() throws Exception {
        String invalidJson = "{customerId: 12345, amount: }";

        mockMvc.perform(post("/api/v1/loan-requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Debe usar la clave de idempotencia del header si esta presente")
    void createLoanRequest_DeberiaUsarIdempotencyKey_DelHeader() throws Exception {
        Long customerId = 11111L;
        BigDecimal amount = new BigDecimal("30000.00");
        Integer termMonths = 18;
        String headerIdempotencyKey = "header-key-123";
        UUID requestId = UUID.randomUUID();

        LoanRequest loanRequest = new LoanRequest(
            requestId,
            customerId,
            amount,
            termMonths,
            LoanStatus.PENDING,
            LocalDate.now(),
            null,
            headerIdempotencyKey
        );

        when(createLoanRequestUseCase.execute(eq(customerId), eq(amount), eq(termMonths), eq(headerIdempotencyKey)))
            .thenReturn(loanRequest);

        Map<String, Object> requestBody = Map.of(
            "customerId", customerId,
            "amount", amount,
            "termMonths", termMonths
        );

        mockMvc.perform(post("/api/v1/loan-requests")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Idempotency-Key", headerIdempotencyKey)
                .content(objectMapper.writeValueAsString(requestBody)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.requestId").value(requestId.toString()));

        verify(createLoanRequestUseCase).execute(customerId, amount, termMonths, headerIdempotencyKey);
    }

    @Test
    @DisplayName("Debe retornar 500 cuando ocurre un error inesperado")
    void createLoanRequest_DeberiaRetornar500_CuandoErrorInesperado() throws Exception {
        Long customerId = 22222L;
        BigDecimal amount = new BigDecimal("40000.00");
        Integer termMonths = 20;
        String idempotencyKey = "key-error";

        when(createLoanRequestUseCase.execute(anyLong(), any(BigDecimal.class), anyInt(), anyString()))
            .thenThrow(new RuntimeException("Error de base de datos"));

        Map<String, Object> requestBody = Map.of(
            "customerId", customerId,
            "amount", amount,
            "termMonths", termMonths,
            "idempotencyKey", idempotencyKey
        );

        mockMvc.perform(post("/api/v1/loan-requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.error").value("INTERNAL_ERROR"));
    }
}