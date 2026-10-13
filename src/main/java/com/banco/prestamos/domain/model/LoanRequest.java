package com.banco.prestamos.domain.model;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LoanRequest(
    @NotNull
    UUID requestId,

    @NotNull
    @Positive
    Long customerId,

    @NotNull
    @Positive
    BigDecimal amount,

    @NotNull
    @Positive
    Integer termMonths,

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "100.0")
    BigDecimal interestRate,

    @NotNull
    @FutureOrPresent
    LocalDate requestDate,

    @NotNull
    LoanStatus status,

    String rejectionReason
) {
    public enum LoanStatus {
        PENDING,
        APPROVED,
        REJECTED,
        CANCELLED
    }

    public LoanRequest {
        if (amount != null && termMonths != null && interestRate != null) {
            BigDecimal monthlyInterestRate = interestRate.divide(BigDecimal.valueOf(100), 10, BigDecimal.ROUND_HALF_UP)
                    .divide(BigDecimal.valueOf(12), 10, BigDecimal.ROUND_HALF_UP);
            BigDecimal denominator = BigDecimal.ONE.subtract(
                    BigDecimal.ONE.divide(
                            BigDecimal.ONE.add(monthlyInterestRate).pow(termMonths),
                            10,
                            BigDecimal.ROUND_HALF_UP
                    )
            );
            BigDecimal monthlyPayment = amount.multiply(monthlyInterestRate)
                    .divide(denominator, 2, BigDecimal.ROUND_HALF_UP);
        }
    }

    public LoanRequest withStatus(LoanStatus newStatus) {
        return new LoanRequest(
                this.requestId,
                this.customerId,
                this.amount,
                this.termMonths,
                this.interestRate,
                this.requestDate,
                newStatus,
                this.rejectionReason
        );
    }

    public LoanRequest withRejectionReason(String reason) {
        return new LoanRequest(
                this.requestId,
                this.customerId,
                this.amount,
                this.termMonths,
                this.interestRate,
                this.requestDate,
                LoanStatus.REJECTED,
                reason
        );
    }
}