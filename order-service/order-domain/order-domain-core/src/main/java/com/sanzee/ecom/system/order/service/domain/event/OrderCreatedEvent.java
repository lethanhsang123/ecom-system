package com.sanzee.ecom.system.order.service.domain.event;

import com.sanzee.ecom.system.domain.event.DomainEvent;
import com.sanzee.ecom.system.domain.event.publisher.DomainEventPublisher;
import com.sanzee.ecom.system.order.service.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderCreatedEvent extends OrderEvent {

    private final DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher;

    public OrderCreatedEvent(Order order, ZonedDateTime createdAt,
                             DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher) {
        super(order, createdAt);
        this.orderCreatedEventDomainEventPublisher = orderCreatedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        this.orderCreatedEventDomainEventPublisher.publish(this);
    }
}
