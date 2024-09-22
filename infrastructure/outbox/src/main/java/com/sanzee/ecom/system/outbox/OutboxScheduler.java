package com.sanzee.ecom.system.outbox;

public interface OutboxScheduler {
    void processOutboxMessage();
}
