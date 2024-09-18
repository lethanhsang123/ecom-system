package com.sanze.ecom.system.payment.service.domain.event;

import com.sanze.ecom.system.payment.service.domain.entity.Payment;
import com.sanzee.ecom.system.domain.event.publisher.DomainEventPublisher;

import java.time.ZonedDateTime;
import java.util.List;

public class PaymentFailedEvent extends PaymentEvent {

    private final DomainEventPublisher<PaymentFailedEvent> publisher;

    public PaymentFailedEvent(Payment payment,
                              ZonedDateTime createdAt,
                              List<String> failureMessages,
                              DomainEventPublisher<PaymentFailedEvent> publisher) {
        super(payment, createdAt, failureMessages);
        this.publisher = publisher;
    }

    @Override
    public void fire() {
        this.publisher.publish(this);
    }
}
