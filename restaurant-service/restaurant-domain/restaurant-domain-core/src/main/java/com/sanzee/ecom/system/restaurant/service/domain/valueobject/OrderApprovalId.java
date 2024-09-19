package com.sanzee.ecom.system.restaurant.service.domain.valueobject;

import com.sanzee.ecom.system.domain.valueobject.BaseId;

import java.util.UUID;

public class OrderApprovalId extends BaseId<UUID> {
    public OrderApprovalId(UUID value) {
        super(value);
    }
}
