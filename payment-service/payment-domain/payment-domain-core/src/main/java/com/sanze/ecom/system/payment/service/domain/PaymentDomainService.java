package com.sanze.ecom.system.payment.service.domain;

import com.sanze.ecom.system.payment.service.domain.entity.CreditEntry;
import com.sanze.ecom.system.payment.service.domain.entity.CreditHistory;
import com.sanze.ecom.system.payment.service.domain.entity.Payment;
import com.sanze.ecom.system.payment.service.domain.event.PaymentEvent;

import java.util.List;

public interface PaymentDomainService {

    PaymentEvent validateAndInitiatePayment(Payment payment,
                                            CreditEntry creditEntry,
                                            List<CreditHistory> creditHistories,
                                            List<String> failureMessages);

    PaymentEvent validateAndCancelPayment(Payment payment,
                                          CreditEntry creditEntry,
                                          List<CreditHistory> creditHistories,
                                          List<String> failureMessage);

}
