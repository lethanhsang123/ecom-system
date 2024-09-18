package com.sanzee.ecom.system.order.service.domain;

import com.sanzee.ecom.system.order.service.domain.dto.create.CreateOrderCommand;
import com.sanzee.ecom.system.order.service.domain.dto.create.CreateOrderResponse;
import com.sanzee.ecom.system.order.service.domain.event.OrderCreatedEvent;
import com.sanzee.ecom.system.order.service.domain.mapper.OrderDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderCreateCommandHandler {

    private final OrderDataMapper orderDataMapper;
    private final OrderCreateHelper orderCreateHelper;

    public OrderCreateCommandHandler(OrderDataMapper orderDataMapper, OrderCreateHelper orderCreateHelper) {
        this.orderDataMapper = orderDataMapper;
        this.orderCreateHelper = orderCreateHelper;
    }

    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        OrderCreatedEvent orderCreatedEvent = orderCreateHelper.persistOrder(createOrderCommand);
        log.info("Order is created with ID: {}", orderCreatedEvent.getOrder().getId().getValue());
        orderCreatedEvent.fire();
        return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder());
    }

}
