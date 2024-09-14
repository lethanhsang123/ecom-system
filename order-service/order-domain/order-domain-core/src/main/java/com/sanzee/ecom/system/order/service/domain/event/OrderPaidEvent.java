package com.sanzee.ecom.system.order.service.domain.event;

import com.sanzee.ecom.system.domain.event.DomainEvent;
import com.sanzee.ecom.system.order.service.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderPaidEvent extends OrderEvent {
    public OrderPaidEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
