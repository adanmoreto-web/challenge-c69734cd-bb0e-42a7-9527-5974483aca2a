package com.banco.prestamos.infrastructure.adapters;

import com.banco.prestamos.domain.ports.IdempotencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class IdempotencyServiceImpl implements IdempotencyService {

    private static final Logger logger = LoggerFactory.getLogger(IdempotencyServiceImpl.class);
    private static final long DEFAULT_TTL_SECONDS = 86400L;

    private final ConcurrentHashMap<String, IdempotencyRecord> idempotencyStore;

    public IdempotencyServiceImpl() {
        this.idempotencyStore = new ConcurrentHashMap<>();
    }

    @Override
    public boolean isIdempotentKeyProcessed(String idempotencyKey) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            return false;
        }
        
        IdempotencyRecord record = idempotencyStore.get(idempotencyKey);
        
        if (record == null) {
            return false;
        }
        
        if (isRecordExpired(record)) {
            logger.debug("Registro de idempotencia expirado para clave: {}", idempotencyKey);
            idempotencyStore.remove(idempotencyKey);
            return false;
        }
        
        logger.debug("Clave de idempotencia encontrada: {}", idempotencyKey);
        return true;
    }

    @Override
    public void markIdempotentKeyAsProcessed(String idempotencyKey, UUID requestId) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new IllegalArgumentException("La clave de idempotencia no puede ser nula o vacía");
        }
        
        if (requestId == null) {
            throw new IllegalArgumentException("El identificador de solicitud no puede ser nulo");
        }
        
        IdempotencyRecord record = new IdempotencyRecord(
            requestId,
            Instant.now(),
            DEFAULT_TTL_SECONDS
        );
        
        idempotencyStore.put(idempotencyKey, record);
        logger.info("Clave de idempotencia marcada como procesada: {} -> {}", idempotencyKey, requestId);
    }

    private boolean isRecordExpired(IdempotencyRecord record) {
        Instant expirationTime = record.createdAt().plusSeconds(record.ttlSeconds());
        return Instant.now().isAfter(expirationTime);
    }

    private record IdempotencyRecord(
        UUID requestId,
        Instant createdAt,
        long ttlSeconds
    ) {}
}