package com.sanzee.ecom.system.payment.service.domain;

import com.sanze.ecom.system.payment.service.domain.PaymentDomainService;
import com.sanze.ecom.system.payment.service.domain.PaymentDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PaymentDomainService paymentDomainService() {
        return new PaymentDomainServiceImpl();
    }

}
