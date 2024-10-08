package com.sanzee.ecom.system.payment.service.domain;

import com.sanze.ecom.system.payment.service.domain.event.PaymentCancelledEvent;
import com.sanze.ecom.system.payment.service.domain.event.PaymentCompletedEvent;
import com.sanze.ecom.system.payment.service.domain.event.PaymentEvent;
import com.sanze.ecom.system.payment.service.domain.event.PaymentFailedEvent;
import com.sanzee.ecom.system.payment.service.domain.dto.PaymentRequest;
import com.sanzee.ecom.system.payment.service.domain.ports.input.message.listener.PaymentRequestMessageListener;
import com.sanzee.ecom.system.payment.service.domain.ports.output.message.publisher.PaymentCancelledMessagePublisher;
import com.sanzee.ecom.system.payment.service.domain.ports.output.message.publisher.PaymentCompletedMessagePublisher;
import com.sanzee.ecom.system.payment.service.domain.ports.output.message.publisher.PaymentFailedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentRequestMessageListenerImpl implements PaymentRequestMessageListener {

    private final PaymentRequestHelper paymentRequestHelper;
    private final PaymentCompletedMessagePublisher paymentCompletedMessagePublisher;
    private final PaymentCancelledMessagePublisher paymentCancelledMessagePublisher;
    private final PaymentFailedMessagePublisher paymentFailedMessagePublisher;

    public PaymentRequestMessageListenerImpl(PaymentRequestHelper paymentRequestHelper,
                                             PaymentCompletedMessagePublisher paymentCompletedMessagePublisher,
                                             PaymentCancelledMessagePublisher paymentCancelledMessagePublisher,
                                             PaymentFailedMessagePublisher paymentFailedMessagePublisher) {
        this.paymentRequestHelper = paymentRequestHelper;
        this.paymentCompletedMessagePublisher = paymentCompletedMessagePublisher;
        this.paymentCancelledMessagePublisher = paymentCancelledMessagePublisher;
        this.paymentFailedMessagePublisher = paymentFailedMessagePublisher;
    }

    @Override
    public void completePayment(PaymentRequest paymentRequest) {
        PaymentEvent paymentEvent = paymentRequestHelper.persistPayment(paymentRequest);
        this.fireEvent(paymentEvent);
    }

    @Override
    public void cancelPayment(PaymentRequest paymentRequest) {
        PaymentEvent paymentEvent = paymentRequestHelper.persistCancelPayment(paymentRequest);
        this.fireEvent(paymentEvent);
    }

    private void fireEvent(PaymentEvent paymentEvent) {
        log.info("Publishing payment com.sanzee.ecom.system.restaurant.service.domain.event with payment id: {} and order id: {}",
                paymentEvent.getPayment().getId().getValue(),
                paymentEvent.getPayment().getOrderId().getValue());
        paymentEvent.fire();
    }

}
