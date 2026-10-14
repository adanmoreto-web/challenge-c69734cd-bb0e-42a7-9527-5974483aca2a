package com.banco.prestamos.domain.model;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "100.0")
    BigDecimal interestRate,

    @NotNull
    LocalDate requestDate,

    @NotNull
    LoanStatus status,

    String rejectionReason,

    String purpose,

    String idempotencyKey,

    LocalDateTime createdAt,

    LocalDateTime updatedAt
) {
    public enum LoanStatus {
        PENDING,
        APPROVED,
        REJECTED,
        CANCELLED
    }

    public LoanRequest {
        if (amount != null && termMonths != null && interestRate != null) {
            BigDecimal monthlyInterestRate = interestRate.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
                    .divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);
            BigDecimal denominator = BigDecimal.ONE.subtract(
                    BigDecimal.ONE.divide(
                            BigDecimal.ONE.add(monthlyInterestRate).pow(termMonths),
                            10,
                            RoundingMode.HALF_UP
                    )
            );
            BigDecimal monthlyPayment = amount.multiply(monthlyInterestRate)
                    .divide(denominator, 2, RoundingMode.HALF_UP);
        }
        if (createdAt == null) {
            createdAt = requestDate != null ? requestDate.atStartOfDay() : LocalDateTime.now();
        }
        if (updatedAt == null) {
            updatedAt = createdAt;
        }
    }

    public LoanRequest(
            UUID requestId,
            Long customerId,
            BigDecimal amount,
            Integer termMonths,
            BigDecimal interestRate,
            LocalDate requestDate,
            LoanStatus status,
            String rejectionReason) {
        this(requestId, customerId, amount, termMonths, interestRate, requestDate, status, rejectionReason,
             null, null, null, null);
    }

    public LoanRequest(
            UUID requestId,
            Long customerId,
            BigDecimal amount,
            Integer termMonths,
            String purpose,
            LoanStatus status,
            LocalDate requestDate,
            String rejectionReason) {
        this(requestId, customerId, amount, termMonths, null, requestDate, status, rejectionReason,
             purpose, null, null, null);
    }

    public LoanRequest(
            UUID requestId,
            Long customerId,
            BigDecimal amount,
            Integer termMonths,
            LoanStatus status,
            LocalDate requestDate,
            String rejectionReason,
            String idempotencyKey) {
        this(requestId, customerId, amount, termMonths, null, requestDate, status, rejectionReason,
             null, idempotencyKey, null, null);
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
                this.rejectionReason,
                this.purpose,
                this.idempotencyKey,
                this.createdAt,
                LocalDateTime.now()
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
                reason,
                this.purpose,
                this.idempotencyKey,
                this.createdAt,
                LocalDateTime.now()
        );
    }
}