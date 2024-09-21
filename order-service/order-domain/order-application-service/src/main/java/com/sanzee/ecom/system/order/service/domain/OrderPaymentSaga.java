package com.sanzee.ecom.system.order.service.domain;

import com.sanzee.ecom.system.domain.event.EmptyEvent;
import com.sanzee.ecom.system.domain.valueobject.OrderId;
import com.sanzee.ecom.system.order.service.domain.dto.message.PaymentResponse;
import com.sanzee.ecom.system.order.service.domain.entity.Order;
import com.sanzee.ecom.system.order.service.domain.event.OrderPaidEvent;
import com.sanzee.ecom.system.order.service.domain.exception.OrderNotFoundException;
import com.sanzee.ecom.system.order.service.domain.ports.output.message.publisher.restaurantapproval.OrderPaidRestaurantRequestMessagePublisher;
import com.sanzee.ecom.system.order.service.domain.ports.output.repository.OrderRepository;
import com.sanzee.ecom.system.saga.SagaStep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class OrderPaymentSaga implements SagaStep<PaymentResponse, OrderPaidEvent, EmptyEvent> {

    private final OrderDomainService orderDomainService;
    private final OrderSagaHelper orderSagaHelper;
    private final OrderPaidRestaurantRequestMessagePublisher orderPaidRestaurantRequestMessagePublisher;

    public OrderPaymentSaga(OrderDomainService orderDomainService, OrderSagaHelper orderSagaHelper,
                            OrderPaidRestaurantRequestMessagePublisher orderPaidRestaurantRequestMessagePublisher) {
        this.orderDomainService = orderDomainService;
        this.orderSagaHelper = orderSagaHelper;
        this.orderPaidRestaurantRequestMessagePublisher = orderPaidRestaurantRequestMessagePublisher;
    }

    @Override
    @Transactional
    public OrderPaidEvent process(PaymentResponse paymentResponse) {
        log.info("Completing payment for order with id: {}", paymentResponse.getOrderId());
        Order order = orderSagaHelper.findOrder(paymentResponse.getOrderId());
        OrderPaidEvent orderPaidEvent = orderDomainService.payOrder(order, orderPaidRestaurantRequestMessagePublisher);
        orderSagaHelper.saveOrder(order);
        log.info("Order with id: {} is paid", order.getId().getValue());
        return orderPaidEvent;
    }

    @Override
    @Transactional
    public EmptyEvent rollback(PaymentResponse paymentResponse) {
        log.info("Cancelling order with id: {}", paymentResponse.getOrderId());
        Order order = orderSagaHelper.findOrder(paymentResponse.getOrderId());
        orderDomainService.cancelOrder(order, paymentResponse.getFailureMessages());
        orderSagaHelper.saveOrder(order);
        return EmptyEvent.INSTANCE;
    }
}
