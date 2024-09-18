package com.sanzee.ecom.system.payment.service.domain.ports.output.repository;

import com.sanze.ecom.system.payment.service.domain.entity.CreditEntry;
import com.sanzee.ecom.system.domain.valueobject.CustomerId;

import java.util.Optional;

public interface CreditEntryRepository {

    CreditEntry save(CreditEntry creditEntry);

    Optional<CreditEntry> findByCustomerId(CustomerId customerId);
}
