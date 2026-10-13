package com.banco.prestamos.infrastructure.adapters;

import com.banco.prestamos.domain.model.LoanRequest;
import com.banco.prestamos.domain.ports.LoanRequestRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaLoanRequestRepository implements LoanRequestRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public LoanRequest save(LoanRequest loanRequest) {
        if (loanRequest.requestId() == null) {
            throw new IllegalArgumentException("El identificador de solicitud no puede ser nulo");
        }
        
        LoanRequest existing = entityManager.find(
            LoanRequest.class, 
            loanRequest.requestId()
        );
        
        if (existing != null) {
            entityManager.merge(loanRequest);
        } else {
            entityManager.persist(loanRequest);
        }
        
        entityManager.flush();
        entityManager.clear();
        
        return entityManager.find(LoanRequest.class, loanRequest.requestId());
    }

    @Override
    public Optional<LoanRequest> findByRequestId(UUID requestId) {
        if (requestId == null) {
            return Optional.empty();
        }
        
        LoanRequest result = entityManager.find(LoanRequest.class, requestId);
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<LoanRequest> findByCustomerIdAndRequestId(Long customerId, UUID requestId) {
        if (customerId == null || requestId == null) {
            return Optional.empty();
        }
        
        var query = entityManager.createQuery(
            "SELECT lr FROM LoanRequest lr WHERE lr.customerId = :customerId AND lr.requestId = :requestId",
            LoanRequest.class
        );
        query.setParameter("customerId", customerId);
        query.setParameter("requestId", requestId);
        
        var results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public boolean existsByCustomerIdAndRequestId(Long customerId, UUID requestId) {
        if (customerId == null || requestId == null) {
            return false;
        }
        
        var query = entityManager.createQuery(
            "SELECT COUNT(lr) FROM LoanRequest lr WHERE lr.customerId = :customerId AND lr.requestId = :requestId",
            Long.class
        );
        query.setParameter("customerId", customerId);
        query.setParameter("requestId", requestId);
        
        Long count = query.getSingleResult();
        return count != null && count > 0;
    }
}