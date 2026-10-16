package com.banco.prestamos.domain.model;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
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
            String purpose,
            LoanStatus status,
            LocalDate requestDate,
            String rejectionReason) {
        this(requestId, customerId, amount, termMonths, requestDate, status, rejectionReason,
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
        this(requestId, customerId, amount, termMonths, requestDate, status, rejectionReason,
             null, idempotencyKey, null, null);
    }

    public LoanRequest withStatus(LoanStatus newStatus) {
        return new LoanRequest(
                this.requestId,
                this.customerId,
                this.amount,
                this.termMonths,
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