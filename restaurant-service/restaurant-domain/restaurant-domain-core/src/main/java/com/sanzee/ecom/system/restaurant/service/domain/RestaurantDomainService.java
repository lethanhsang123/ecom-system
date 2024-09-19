package com.sanzee.ecom.system.restaurant.service.domain;

import com.sanzee.ecom.system.domain.event.publisher.DomainEventPublisher;
import com.sanzee.ecom.system.restaurant.service.domain.entity.Restaurant;
import com.sanzee.ecom.system.restaurant.service.domain.event.OrderApprovalEvent;
import com.sanzee.ecom.system.restaurant.service.domain.event.OrderApprovedEvent;
import com.sanzee.ecom.system.restaurant.service.domain.event.OrderRejectedEvent;

import java.util.List;

public interface RestaurantDomainService {

    OrderApprovalEvent validateOrder(Restaurant restaurant, List<String> failureMessages,
                                     DomainEventPublisher<OrderApprovedEvent> orderApprovedEventDomainEventPublisher,
                                     DomainEventPublisher<OrderRejectedEvent> orderRejectedEventDomainEventPublisher);
}
