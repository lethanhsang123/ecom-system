package com.sanzee.ecom.system.order.service.domain;

import com.sanzee.ecom.system.order.service.domain.dto.message.CustomerModel;
import com.sanzee.ecom.system.order.service.domain.ports.input.message.listener.customer.CustomerMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerMessageListenerImpl implements CustomerMessageListener {

    @Override
    public void customerCreated(CustomerModel customerModel) {
    }
}
