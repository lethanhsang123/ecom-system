package com.sanzee.ecom.system.payment.service.domain.ports.output.repository;

import com.sanze.ecom.system.payment.service.domain.entity.CreditHistory;
import com.sanzee.ecom.system.domain.valueobject.CustomerId;

import java.util.List;
import java.util.Optional;

public interface CreditHistoryRepository {

    CreditHistory save(CreditHistory creditHistory);

    Optional<List<CreditHistory>> findByCustomerId(CustomerId customerId);
}
