package com.sanze.ecom.system.payment.service.domain.exception;

import com.sanzee.ecom.system.domain.exception.DomainException;

public class PaymentDomainException extends DomainException {
    public PaymentDomainException(String message) {
        super(message);
    }

    public PaymentDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
