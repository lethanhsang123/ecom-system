package com.sanzee.ecom.system.restaurant.service.domain.ports.input.message.listener;

import com.sanzee.ecom.system.restaurant.service.domain.dto.RestaurantApprovalRequest;

public interface RestaurantApprovalRequestMessageListener {
    void approveOrder(RestaurantApprovalRequest restaurantApprovalRequest);
}
