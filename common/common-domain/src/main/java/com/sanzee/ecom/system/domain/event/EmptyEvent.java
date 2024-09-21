package com.sanzee.ecom.system.domain.event;

public class EmptyEvent implements DomainEvent<Void> {

    public static final EmptyEvent INSTANCE = new EmptyEvent();

    private EmptyEvent() {}

    @Override
    public void fire() {

    }
}
