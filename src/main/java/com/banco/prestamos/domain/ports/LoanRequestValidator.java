package com.banco.prestamos.domain.ports;

import java.math.BigDecimal;

public interface LoanRequestValidator {
    void validate(Long customerId, BigDecimal amount, Integer termMonths, String idempotencyKey);
}
