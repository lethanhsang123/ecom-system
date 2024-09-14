package com.sanzee.ecom.system.order.service.domain.ports.output.message.publisher.restaurantapproval;

import com.sanzee.ecom.system.domain.event.publisher.DomainEventPublisher;
import com.sanzee.ecom.system.order.service.domain.event.OrderPaidEvent;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
