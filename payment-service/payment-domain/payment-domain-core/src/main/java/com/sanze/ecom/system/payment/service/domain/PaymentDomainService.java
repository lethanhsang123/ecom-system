package com.sanze.ecom.system.payment.service.domain;

import com.sanze.ecom.system.payment.service.domain.entity.CreditEntry;
import com.sanze.ecom.system.payment.service.domain.entity.CreditHistory;
import com.sanze.ecom.system.payment.service.domain.entity.Payment;
import com.sanze.ecom.system.payment.service.domain.event.PaymentCancelledEvent;
import com.sanze.ecom.system.payment.service.domain.event.PaymentCompletedEvent;
import com.sanze.ecom.system.payment.service.domain.event.PaymentEvent;
import com.sanze.ecom.system.payment.service.domain.event.PaymentFailedEvent;
import com.sanzee.ecom.system.domain.event.publisher.DomainEventPublisher;

import java.util.List;

public interface PaymentDomainService {

    PaymentEvent validateAndInitiatePayment(Payment payment,
                                            CreditEntry creditEntry,
                                            List<CreditHistory> creditHistories,
                                            List<String> failureMessages,
                                            DomainEventPublisher<PaymentCompletedEvent> paymentCompletedEventDomainEventPublisher,
                                            DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher);

    PaymentEvent validateAndCancelPayment(Payment payment,
                                          CreditEntry creditEntry,
                                          List<CreditHistory> creditHistories,
                                          List<String> failureMessage,
                                          DomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher,
                                          DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher);

}
