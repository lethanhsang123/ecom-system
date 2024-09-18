package com.sanzee.ecom.system.payment.service.domain.ports.input.message.listener;

import com.sanzee.ecom.system.payment.service.domain.dto.PaymentRequest;

public interface PaymentRequestMessageListener {

    void completePayment(PaymentRequest paymentRequest);

    void cancelPayment(PaymentRequest paymentRequest);
}
