package com.sanzee.ecom.system.order.service.domain.outbox.model.payment;

import com.sanzee.ecom.system.domain.valueobject.OrderStatus;
import com.sanzee.ecom.system.outbox.OutboxStatus;
import com.sanzee.ecom.system.saga.SagaStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class OrderPaymentOutboxMessage {

    private UUID id;
    private UUID sagaId;
    private ZonedDateTime createdAt;
    private ZonedDateTime processedAt;
    private String type;
    private String payload;
    private SagaStatus sagaStatus;
    private OrderStatus orderStatus;
    private OutboxStatus outboxStatus;
    private int version;

    public void setProcessedAt(ZonedDateTime processedAt) {
        this.processedAt = processedAt;
    }

    public void setSagaStatus(SagaStatus sagaStatus) {
        this.sagaStatus = sagaStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOutboxStatus(OutboxStatus outboxStatus) {
        this.outboxStatus = outboxStatus;
    }

}
