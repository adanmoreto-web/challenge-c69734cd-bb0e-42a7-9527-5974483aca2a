package com.banco.prestamos.application.usecases;

import com.banco.prestamos.domain.model.LoanRequest;
import com.banco.prestamos.domain.model.LoanRequest.LoanStatus;
import com.banco.prestamos.domain.ports.IdempotencyService;
import com.banco.prestamos.domain.ports.LoanRequestRepository;
import com.banco.prestamos.infrastructure.exceptions.LoanRequestAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class CreateLoanRequestUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CreateLoanRequestUseCase.class);
    
    private static final BigDecimal MIN_AMOUNT = new BigDecimal("1000.00");
    private static final BigDecimal MAX_AMOUNT = new BigDecimal("50000.00");
    private static final int MIN_TERM_MONTHS = 6;
    private static final int MAX_TERM_MONTHS = 60;

    private final LoanRequestRepository loanRequestRepository;
    private final IdempotencyService idempotencyService;

    public CreateLoanRequestUseCase(
            LoanRequestRepository loanRequestRepository,
            IdempotencyService idempotencyService) {
        this.loanRequestRepository = loanRequestRepository;
        this.idempotencyService = idempotencyService;
    }

    @Transactional
    public LoanRequest execute(
            Long customerId,
            String idempotencyKey,
            BigDecimal amount,
            Integer termMonths,
            String purpose) {
        
        logger.info("Iniciando procesamiento de solicitud de préstamo para cliente: {}", customerId);
        
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new IllegalArgumentException("La clave de idempotencia es obligatoria");
        }
        
        if (idempotencyService.isIdempotentKeyProcessed(idempotencyKey)) {
            logger.warn("Clave de idempotencia ya procesada: {}", idempotencyKey);
            throw new LoanRequestAlreadyExistsException(
                "Ya existe una solicitud procesada con esta clave de idempotencia"
            );
        }
        
        validateLoanParameters(amount, termMonths, purpose);
        
        UUID requestId = UUID.randomUUID();
        LocalDate requestDate = LocalDate.now();
        
        LoanRequest loanRequest = new LoanRequest(
            requestId,
            customerId,
            amount,
            termMonths,
            purpose,
            LoanStatus.PENDING,
            requestDate,
            null
        );
        
        LoanRequest savedRequest = loanRequestRepository.save(loanRequest);
        
        idempotencyService.markIdempotentKeyAsProcessed(idempotencyKey, requestId);
        
        logger.info("Solicitud de préstamo creada exitosamente con ID: {}", requestId);
        
        return savedRequest;
    }

    private void validateLoanParameters(BigDecimal amount, Integer termMonths, String purpose) {
        if (amount == null) {
            throw new IllegalArgumentException("El monto del préstamo es obligatorio");
        }
        
        if (amount.compareTo(MIN_AMOUNT) < 0) {
            throw new IllegalArgumentException(
                String.format("El monto mínimo del préstamo es %s", MIN_AMOUNT)
            );
        }
        
        if (amount.compareTo(MAX_AMOUNT) > 0) {
            throw new IllegalArgumentException(
                String.format("El monto máximo del préstamo es %s", MAX_AMOUNT)
            );
        }
        
        if (termMonths == null) {
            throw new IllegalArgumentException("El plazo del préstamo es obligatorio");
        }
        
        if (termMonths < MIN_TERM_MONTHS || termMonths > MAX_TERM_MONTHS) {
            throw new IllegalArgumentException(
                String.format(
                    "El plazo debe estar entre %d y %d meses",
                    MIN_TERM_MONTHS,
                    MAX_TERM_MONTHS
                )
            );
        }
        
        if (purpose == null || purpose.isBlank()) {
            throw new IllegalArgumentException("El propósito del préstamo es obligatorio");
        }
    }
}