package com.banco.prestamos.application.usecases;

import com.banco.prestamos.domain.ports.LoanRequestValidator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DefaultLoanRequestValidator implements LoanRequestValidator {

    @Override
    public void validate(Long customerId, BigDecimal amount, Integer termMonths, String idempotencyKey) {
        if (customerId == null) {
            throw new IllegalArgumentException("El ID del cliente es obligatorio");
        }
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new IllegalArgumentException("La clave de idempotencia es obligatoria");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del préstamo debe ser mayor a cero");
        }
        if (termMonths == null || termMonths <= 0) {
            throw new IllegalArgumentException("El plazo del préstamo debe ser mayor a cero");
        }
    }
}
