package com.sanzee.ecom.system.payment.service.messaging.mapper;

import com.sanze.ecom.system.payment.service.domain.event.PaymentEvent;
import com.sanzee.ecom.system.domain.valueobject.PaymentOrderStatus;
import com.sanzee.ecom.system.kafka.order.avro.model.PaymentRequestAvroModel;
import com.sanzee.ecom.system.kafka.order.avro.model.PaymentResponseAvroModel;
import com.sanzee.ecom.system.kafka.order.avro.model.PaymentStatus;
import com.sanzee.ecom.system.payment.service.domain.dto.PaymentRequest;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentMessagingDataMapper {

    public PaymentResponseAvroModel paymentEventToPaymentResponseAvroModel(PaymentEvent paymentCompletedEvent) {
        return PaymentResponseAvroModel.newBuilder()
                .setId(UUID.randomUUID())
                .setSagaId(UUID.fromString(Strings.EMPTY))
                .setPaymentId(paymentCompletedEvent.getPayment().getId().getValue())
                .setCustomerId(paymentCompletedEvent.getPayment().getCustomerId().getValue())
                .setOrderId(paymentCompletedEvent.getPayment().getOrderId().getValue())
                .setPrice(paymentCompletedEvent.getPayment().getPrice().getAmount())
                .setCreatedAt(paymentCompletedEvent.getCreatedAt().toInstant())
                .setPaymentStatus(PaymentStatus.valueOf(paymentCompletedEvent.getPayment().getPaymentStatus().name()))
                .setFailureMessages(paymentCompletedEvent.getFailureMessages())
                .build();
    }

    public PaymentRequest paymentRequestAvroModelToPaymentRequest(PaymentRequestAvroModel paymentRequestAvroModel) {
        return PaymentRequest.builder()
                .id(paymentRequestAvroModel.getId().toString())
                .sagaId(paymentRequestAvroModel.getSagaId().toString())
                .customerId(paymentRequestAvroModel.getCustomerId().toString())
                .orderId(paymentRequestAvroModel.getOrderId().toString())
                .price(paymentRequestAvroModel.getPrice())
                .createdAt(paymentRequestAvroModel.getCreatedAt())
                .paymentOrderStatus(PaymentOrderStatus.valueOf(paymentRequestAvroModel.getPaymentOrderStatus().name()))
                .build();
    }
}
