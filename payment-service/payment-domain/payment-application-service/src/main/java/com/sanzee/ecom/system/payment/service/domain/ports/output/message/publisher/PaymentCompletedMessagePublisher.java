package com.sanzee.ecom.system.payment.service.domain.ports.output.message.publisher;

import com.sanze.ecom.system.payment.service.domain.event.PaymentCompletedEvent;
import com.sanzee.ecom.system.domain.event.publisher.DomainEventPublisher;

public interface PaymentCompletedMessagePublisher extends DomainEventPublisher<PaymentCompletedEvent> {
}
