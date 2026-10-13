package com.banco.prestamos.domain.ports;

import java.util.UUID;

public interface IdempotencyService {
    boolean isIdempotentKeyProcessed(String idempotencyKey);

    void markIdempotentKeyAsProcessed(String idempotencyKey, UUID requestId);
}