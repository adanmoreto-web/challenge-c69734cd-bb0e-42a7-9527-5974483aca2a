package com.banco.prestamos.application.usecases;

import com.banco.prestamos.domain.model.LoanRequest;
import com.banco.prestamos.domain.model.LoanRequest.LoanStatus;
import com.banco.prestamos.domain.ports.IdempotencyService;
import com.banco.prestamos.domain.ports.LoanRequestRepository;
import com.banco.prestamos.infrastructure.exceptions.LoanRequestAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests para CreateLoanRequestUseCase")
class CreateLoanRequestUseCaseTest {

    @Mock
    private LoanRequestRepository loanRequestRepository;

    @Mock
    private IdempotencyService idempotencyService;

    private CreateLoanRequestUseCase createLoanRequestUseCase;

    @BeforeEach
    void setUp() {
        createLoanRequestUseCase = new CreateLoanRequestUseCase(loanRequestRepository, idempotencyService);
    }

    @Test
    @DisplayName("Debe crear una solicitud de prestamo exitosamente cuando es idempotente")
    void execute_DeberiaCrearSolicitud_CuandoEsIdempotente() {
        Long customerId = 12345L;
        BigDecimal amount = new BigDecimal("50000.00");
        Integer termMonths = 24;
        String idempotencyKey = "idem-001";
        UUID requestId = UUID.randomUUID();

        when(idempotencyService.isIdempotentKeyProcessed(idempotencyKey)).thenReturn(false);
        when(loanRequestRepository.existsByCustomerIdAndRequestId(customerId, requestId)).thenReturn(false);
        when(loanRequestRepository.save(any(LoanRequest.class))).thenAnswer(invocation -> {
            LoanRequest saved = invocation.getArgument(0);
            return saved.withStatus(LoanStatus.PENDING);
        });

        LoanRequest result = createLoanRequestUseCase.execute(customerId, amount, termMonths, idempotencyKey);

        assertNotNull(result);
        assertEquals(customerId, result.customerId());
        assertEquals(amount, result.amount());
        assertEquals(termMonths, result.termMonths());
        assertEquals(LoanStatus.PENDING, result.status());
        verify(idempotencyService).markIdempotentKeyAsProcessed(eq(idempotencyKey), any(UUID.class));
    }

    @Test
    @DisplayName("Debe rechazar solicitud duplicada cuando ya existe para el mismo cliente y requestId")
    void execute_DeberiaRechazar_CuandoSolicitudDuplicada() {
        Long customerId = 12345L;
        BigDecimal amount = new BigDecimal("50000.00");
        Integer termMonths = 24;
        String idempotencyKey = "idem-002";
        UUID requestId = UUID.randomUUID();

        when(idempotencyService.isIdempotentKeyProcessed(idempotencyKey)).thenReturn(true);
        when(loanRequestRepository.findByCustomerIdAndRequestId(customerId, requestId))
            .thenReturn(Optional.of(new LoanRequest(
                requestId,
                customerId,
                amount,
                termMonths,
                LoanStatus.PENDING,
                LocalDate.now(),
                null,
                idempotencyKey
            )));

        LoanRequestAlreadyExistsException exception = assertThrows(
            LoanRequestAlreadyExistsException.class,
            () -> createLoanRequestUseCase.execute(customerId, amount, termMonths, idempotencyKey)
        );

        assertEquals(customerId, exception.getCustomerId());
        assertEquals(requestId, exception.getRequestId());
        verify(loanRequestRepository, never()).save(any(LoanRequest.class));
    }

    @Test
    @DisplayName("Debe rechazar cuando el monto es menor o igual a cero")
    void execute_DeberiaRechazar_CuandoMontoInvalido() {
        Long customerId = 12345L;
        BigDecimal invalidAmount = BigDecimal.ZERO;
        Integer termMonths = 24;
        String idempotencyKey = "idem-003";

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> createLoanRequestUseCase.execute(customerId, invalidAmount, termMonths, idempotencyKey)
        );

        assertTrue(exception.getMessage().contains("monto") || exception.getMessage().contains("amount"));
        verify(loanRequestRepository, never()).save(any(LoanRequest.class));
    }

    @Test
    @DisplayName("Debe rechazar cuando el plazo es menor o igual a cero")
    void execute_DeberiaRechazar_CuandoPlazoInvalido() {
        Long customerId = 12345L;
        BigDecimal amount = new BigDecimal("50000.00");
        Integer invalidTerm = 0;
        String idempotencyKey = "idem-004";

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> createLoanRequestUseCase.execute(customerId, amount, invalidTerm, idempotencyKey)
        );

        assertTrue(exception.getMessage().contains("plazo") || exception.getMessage().contains("term"));
        verify(loanRequestRepository, never()).save(any(LoanRequest.class));
    }

    @Test
    @DisplayName("Debe rechazar cuando el customerId es null")
    void execute_DeberiaRechazar_CuandoCustomerIdNulo() {
        Long nullCustomerId = null;
        BigDecimal amount = new BigDecimal("50000.00");
        Integer termMonths = 24;
        String idempotencyKey = "idem-005";

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> createLoanRequestUseCase.execute(nullCustomerId, amount, termMonths, idempotencyKey)
        );

        assertTrue(exception.getMessage().contains("cliente") || exception.getMessage().contains("customer"));
    }

    @Test
    @DisplayName("Debe rechazar cuando la clave de idempotencia es nula o vacia")
    void execute_DeberiaRechazar_CuandoIdempotencyKeyInvalido() {
        Long customerId = 12345L;
        BigDecimal amount = new BigDecimal("50000.00");
        Integer termMonths = 24;
        String emptyIdempotencyKey = "";

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> createLoanRequestUseCase.execute(customerId, amount, termMonths, emptyIdempotencyKey)
        );

        assertTrue(exception.getMessage().contains("idempotency") || exception.getMessage().contains("clave"));
    }

    @Test
    @DisplayName("Debe guardar la solicitud con la clave de idempotencia correcta")
    void execute_DeberiaGuardarConIdempotencyKey_Correcto() {
        Long customerId = 67890L;
        BigDecimal amount = new BigDecimal("100000.00");
        Integer termMonths = 36;
        String idempotencyKey = "idem-006-unique";

        when(idempotencyService.isIdempotentKeyProcessed(idempotencyKey)).thenReturn(false);
        when(loanRequestRepository.existsByCustomerIdAndRequestId(anyLong(), any(UUID.class))).thenReturn(false);
        when(loanRequestRepository.save(any(LoanRequest.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ArgumentCaptor<LoanRequest> requestCaptor = ArgumentCaptor.forClass(LoanRequest.class);
        createLoanRequestUseCase.execute(customerId, amount, termMonths, idempotencyKey);

        verify(loanRequestRepository).save(requestCaptor.capture());
        LoanRequest capturedRequest = requestCaptor.getValue();
        assertEquals(idempotencyKey, capturedRequest.idempotencyKey());
    }

    @Test
    @DisplayName("Debe rechazar cuando la clave de idempotencia ya fue procesada previamente")
    void execute_DeberiaRechazar_CuandoIdempotencyKeyYaProcesada() {
        Long customerId = 11111L;
        BigDecimal amount = new BigDecimal("25000.00");
        Integer termMonths = 12;
        String idempotencyKey = "idem-007-processed";
        UUID existingRequestId = UUID.randomUUID();

        when(idempotencyService.isIdempotentKeyProcessed(idempotencyKey)).thenReturn(true);
        when(loanRequestRepository.findByCustomerIdAndRequestId(eq(customerId), eq(existingRequestId)))
            .thenReturn(Optional.of(new LoanRequest(
                existingRequestId,
                customerId,
                amount,
                termMonths,
                LoanStatus.APPROVED,
                LocalDate.now(),
                null,
                idempotencyKey
            )));

        LoanRequestAlreadyExistsException exception = assertThrows(
            LoanRequestAlreadyExistsException.class,
            () -> createLoanRequestUseCase.execute(customerId, amount, termMonths, idempotencyKey)
        );

        assertEquals(idempotencyKey, exception.getIdempotencyKey());
        verify(loanRequestRepository, never()).save(any(LoanRequest.class));
    }
}