package com.sanzee.ecom.system.payment.service.domain.ports.output.message.publisher;

import com.sanze.ecom.system.payment.service.domain.event.PaymentCancelledEvent;
import com.sanzee.ecom.system.domain.event.publisher.DomainEventPublisher;

public interface PaymentCancelledMessagePublisher extends DomainEventPublisher<PaymentCancelledEvent> {
}
