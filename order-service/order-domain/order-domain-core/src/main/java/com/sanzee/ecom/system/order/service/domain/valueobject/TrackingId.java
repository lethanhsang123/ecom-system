package com.sanzee.ecom.system.order.service.domain.valueobject;

import com.sanzee.ecom.system.domain.valueobject.BaseId;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> {
    public TrackingId(UUID value) {
        super(value);
    }
}
