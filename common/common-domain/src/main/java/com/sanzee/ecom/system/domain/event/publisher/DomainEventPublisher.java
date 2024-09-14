package com.sanzee.ecom.system.domain.event.publisher;

import com.sanzee.ecom.system.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T domainEvent);
}
