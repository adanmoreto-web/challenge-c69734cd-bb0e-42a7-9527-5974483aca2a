package com.banco.prestamos.domain.ports;

import com.banco.prestamos.domain.model.LoanRequest;
import java.util.Optional;
import java.util.UUID;

public interface LoanRequestRepository {
    LoanRequest save(LoanRequest loanRequest);

    Optional<LoanRequest> findByRequestId(UUID requestId);

    java.util.List<LoanRequest> findByCustomerId(Long customerId);

    Optional<LoanRequest> findByCustomerIdAndRequestId(Long customerId, UUID requestId);

    boolean existsByCustomerIdAndRequestId(Long customerId, UUID requestId);
}