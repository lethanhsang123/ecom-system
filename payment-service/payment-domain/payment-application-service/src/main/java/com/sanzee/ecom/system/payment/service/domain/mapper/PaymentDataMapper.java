package com.sanzee.ecom.system.payment.service.domain.mapper;

import com.sanze.ecom.system.payment.service.domain.entity.Payment;
import com.sanzee.ecom.system.domain.valueobject.CustomerId;
import com.sanzee.ecom.system.domain.valueobject.Money;
import com.sanzee.ecom.system.domain.valueobject.OrderId;
import com.sanzee.ecom.system.payment.service.domain.dto.PaymentRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentDataMapper {

    public Payment paymentRequestModelToPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .orderId(new OrderId(UUID.fromString(paymentRequest.getOrderId())))
                .customerId(new CustomerId(UUID.fromString(paymentRequest.getCustomerId())))
                .price(new Money(paymentRequest.getPrice()))
                .build();
    }

}
