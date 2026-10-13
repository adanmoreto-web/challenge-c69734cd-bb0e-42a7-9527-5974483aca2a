package com.banco.prestamos.infrastructure.exceptions;

import java.util.UUID;

public class LoanRequestAlreadyExistsException extends RuntimeException {

    private final Long customerId;
    private final UUID requestId;
    private final String idempotencyKey;

    public LoanRequestAlreadyExistsException(Long customerId, UUID requestId, String idempotencyKey) {
        super(buildMessage(customerId, requestId, idempotencyKey));
        this.customerId = customerId;
        this.requestId = requestId;
        this.idempotencyKey = idempotencyKey;
        validateInvariant();
    }

    public LoanRequestAlreadyExistsException(Long customerId, UUID requestId, String idempotencyKey, Throwable cause) {
        super(buildMessage(customerId, requestId, idempotencyKey), cause);
        this.customerId = customerId;
        this.requestId = requestId;
        this.idempotencyKey = idempotencyKey;
        validateInvariant();
    }

    private static String buildMessage(Long customerId, UUID requestId, String idempotencyKey) {
        return String.format(
            "Ya existe una solicitud de prestamo para el cliente %d con requestId %s (idempotencyKey: %s)",
            customerId,
            requestId != null ? requestId.toString() : "null",
            idempotencyKey != null ? idempotencyKey : "null"
        );
    }

    private void validateInvariant() {
        if (customerId == null) {
            throw new IllegalArgumentException("El customerId no puede ser null en LoanRequestAlreadyExistsException");
        }
        if (requestId == null && (idempotencyKey == null || idempotencyKey.isBlank())) {
            throw new IllegalArgumentException("Se requiere al menos requestId o idempotencyKey para LoanRequestAlreadyExistsException");
        }
    }

    public Long getCustomerId() {
        return customerId;
    }

    public UUID getRequestId() {
        return requestId;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public String getErrorCode() {
        return "LOAN_REQUEST_DUPLICATE";
    }
}