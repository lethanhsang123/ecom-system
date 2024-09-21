package com.sanzee.ecom.system.saga;

import com.sanzee.ecom.system.domain.event.DomainEvent;

public interface SagaStep <T, S extends DomainEvent<?>, U extends DomainEvent<?> > {

    S process(T data);
    U rollback(T data);

}
