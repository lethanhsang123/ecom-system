package com.sanze.ecom.system.payment.service.domain.valueobject;

import com.sanzee.ecom.system.domain.valueobject.BaseId;

import java.util.UUID;

public class CreditEntryId extends BaseId<UUID> {
    public CreditEntryId(UUID value) {
        super(value);
    }
}
