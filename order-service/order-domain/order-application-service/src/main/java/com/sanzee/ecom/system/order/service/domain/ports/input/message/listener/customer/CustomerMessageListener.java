package com.sanzee.ecom.system.order.service.domain.ports.input.message.listener.customer;

import com.sanzee.ecom.system.order.service.domain.dto.message.CustomerModel;

public interface CustomerMessageListener {

    void customerCreated(CustomerModel customerModel);
}
