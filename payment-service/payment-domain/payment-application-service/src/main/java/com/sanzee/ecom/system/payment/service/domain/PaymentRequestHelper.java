package com.sanzee.ecom.system.payment.service.domain;

import com.sanze.ecom.system.payment.service.domain.PaymentDomainService;
import com.sanze.ecom.system.payment.service.domain.entity.CreditEntry;
import com.sanze.ecom.system.payment.service.domain.entity.CreditHistory;
import com.sanze.ecom.system.payment.service.domain.entity.Payment;
import com.sanze.ecom.system.payment.service.domain.event.PaymentCompletedEvent;
import com.sanze.ecom.system.payment.service.domain.event.PaymentEvent;
import com.sanze.ecom.system.payment.service.domain.exception.PaymentNotFoundException;
import com.sanzee.ecom.system.domain.valueobject.CustomerId;
import com.sanzee.ecom.system.payment.service.domain.dto.PaymentRequest;
import com.sanzee.ecom.system.payment.service.domain.exception.PaymentApplicationServiceException;
import com.sanzee.ecom.system.payment.service.domain.mapper.PaymentDataMapper;
import com.sanzee.ecom.system.payment.service.domain.ports.output.message.publisher.PaymentCancelledMessagePublisher;
import com.sanzee.ecom.system.payment.service.domain.ports.output.message.publisher.PaymentCompletedMessagePublisher;
import com.sanzee.ecom.system.payment.service.domain.ports.output.message.publisher.PaymentFailedMessagePublisher;
import com.sanzee.ecom.system.payment.service.domain.ports.output.repository.CreditEntryRepository;
import com.sanzee.ecom.system.payment.service.domain.ports.output.repository.CreditHistoryRepository;
import com.sanzee.ecom.system.payment.service.domain.ports.output.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Component
public class PaymentRequestHelper {

    private final PaymentDomainService paymentDomainService;
    private final PaymentDataMapper paymentDataMapper;
    private final PaymentRepository paymentRepository;
    private final CreditEntryRepository creditEntryRepository;
    private final CreditHistoryRepository creditHistoryRepository;
    private final PaymentCompletedMessagePublisher paymentCompletedMessagePublisher;
    private final PaymentCancelledMessagePublisher paymentCancelledMessagePublisher;
    private final PaymentFailedMessagePublisher paymentFailedMessagePublisher;

    public PaymentRequestHelper(PaymentDomainService paymentDomainService, PaymentDataMapper paymentDataMapper,
                                PaymentRepository paymentRepository, CreditEntryRepository creditEntryRepository,
                                CreditHistoryRepository creditHistoryRepository,
                                PaymentCompletedMessagePublisher paymentCompletedMessagePublisher,
                                PaymentCancelledMessagePublisher paymentCancelledMessagePublisher,
                                PaymentFailedMessagePublisher paymentFailedMessagePublisher) {
        this.paymentDomainService = paymentDomainService;
        this.paymentDataMapper = paymentDataMapper;
        this.paymentRepository = paymentRepository;
        this.creditEntryRepository = creditEntryRepository;
        this.creditHistoryRepository = creditHistoryRepository;
        this.paymentCompletedMessagePublisher = paymentCompletedMessagePublisher;
        this.paymentCancelledMessagePublisher = paymentCancelledMessagePublisher;
        this.paymentFailedMessagePublisher = paymentFailedMessagePublisher;
    }

    @Transactional
    public PaymentEvent persistPayment(PaymentRequest paymentRequest) {
        log.info("Received payment complete com.sanzee.ecom.system.restaurant.service.domain.event for order id: {}", paymentRequest.getOrderId());
        Payment payment = paymentDataMapper.paymentRequestModelToPayment(paymentRequest);
        CreditEntry creditEntry = getCreditEntry(payment.getCustomerId());
        List<CreditHistory> creditHistories = getCreditHistory(payment.getCustomerId());
        List<String> failureMessages = new ArrayList<>();
        PaymentEvent paymentEvent =
                paymentDomainService.validateAndInitiatePayment(payment, creditEntry, creditHistories, failureMessages,
                        paymentCompletedMessagePublisher, paymentFailedMessagePublisher);
        persistDbObjects(payment, creditEntry, creditHistories, failureMessages);

        if (failureMessages.isEmpty()) {
            creditEntryRepository.save(creditEntry);
            creditHistoryRepository.save(creditHistories.get(creditHistories.size() - 1));
        }
        return paymentEvent;
    }

    @Transactional
    public PaymentEvent persistCancelPayment(PaymentRequest paymentRequest) {
        log.info("Received payment rollback com.sanzee.ecom.system.restaurant.service.domain.event for order id: {}", paymentRequest.getOrderId());
        Optional<Payment> paymentResponse = paymentRepository
                .findByOrderId(UUID.fromString(paymentRequest.getOrderId()));
        if (paymentResponse.isEmpty()) {
            log.error("Payment with order id: {} could not be found!", paymentRequest.getOrderId());
            throw new PaymentNotFoundException("Payment with order id: " +
                    paymentRequest.getOrderId() + " could not be found!");
        }
        Payment payment = paymentResponse.get();
        CreditEntry creditEntry = getCreditEntry(payment.getCustomerId());
        List<CreditHistory> creditHistories = getCreditHistory(payment.getCustomerId());
        List<String> failureMessages = new ArrayList<>();
        PaymentEvent paymentEvent = paymentDomainService
                .validateAndCancelPayment(payment, creditEntry, creditHistories, failureMessages,
                        paymentCancelledMessagePublisher, paymentFailedMessagePublisher);
        persistDbObjects(payment, creditEntry, creditHistories, failureMessages);

        return paymentEvent;

    }

    private CreditEntry getCreditEntry(CustomerId customerId) {
        Optional<CreditEntry> creditEntry = creditEntryRepository.findByCustomerId(customerId);
        if (creditEntry.isEmpty()) {
            log.error("Could not find credit entry for customer: {}", customerId.getValue());
            throw new PaymentApplicationServiceException("Could not find credit entry for customer: " +
                    customerId.getValue());
        }
        return creditEntry.get();
    }

    private List<CreditHistory> getCreditHistory(CustomerId customerId) {
        Optional<List<CreditHistory>> creditHistories = creditHistoryRepository.findByCustomerId(customerId);
        if (creditHistories.isEmpty()) {
            log.error("Could not find credit history for customer: {}", customerId.getValue());
            throw new PaymentApplicationServiceException("Could not find credit history for customer: " +
                    customerId.getValue());
        }
        return creditHistories.get();
    }

    private void persistDbObjects(Payment payment,
                                  CreditEntry creditEntry,
                                  List<CreditHistory> creditHistories,
                                  List<String> failureMessages) {
        paymentRepository.save(payment);
        if (failureMessages.isEmpty()) {
            creditEntryRepository.save(creditEntry);
            creditHistoryRepository.save(creditHistories.get(creditHistories.size() - 1));
        }
    }

}
