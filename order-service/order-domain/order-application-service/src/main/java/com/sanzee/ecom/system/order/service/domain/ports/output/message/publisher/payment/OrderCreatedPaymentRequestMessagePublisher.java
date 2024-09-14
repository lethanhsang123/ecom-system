package com.sanzee.ecom.system.order.service.domain.ports.output.message.publisher.payment;

import com.sanzee.ecom.system.domain.event.publisher.DomainEventPublisher;
import com.sanzee.ecom.system.order.service.domain.event.OrderCreatedEvent;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {
}
