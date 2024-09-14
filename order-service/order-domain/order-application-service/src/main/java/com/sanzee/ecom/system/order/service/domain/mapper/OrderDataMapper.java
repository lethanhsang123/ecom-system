package com.sanzee.ecom.system.order.service.domain.mapper;

import com.sanzee.ecom.system.domain.valueobject.CustomerId;
import com.sanzee.ecom.system.domain.valueobject.Money;
import com.sanzee.ecom.system.domain.valueobject.ProductId;
import com.sanzee.ecom.system.domain.valueobject.RestaurantId;
import com.sanzee.ecom.system.order.service.domain.dto.create.CreateOrderCommand;
import com.sanzee.ecom.system.order.service.domain.dto.create.CreateOrderResponse;
import com.sanzee.ecom.system.order.service.domain.dto.create.OrderAddress;
import com.sanzee.ecom.system.order.service.domain.dto.message.CustomerModel;
import com.sanzee.ecom.system.order.service.domain.entity.*;
import com.sanzee.ecom.system.order.service.domain.valueobject.StreetAddress;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataMapper {

    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
        return Restaurant.builder()
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .products(createOrderCommand.getItems().stream()
                        .map(orderItem -> Product.builder().id(new ProductId(orderItem.getProductId())).build()).collect(Collectors.toList())
                )
                .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.builder()
                .customerId(new CustomerId(createOrderCommand.getCustomerId()))
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
                .price(new Money(createOrderCommand.getPrice()))
                .items(orderItemsToOrderItemEntities(createOrderCommand.getItems()))
                .build();
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order) {
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    public Customer customerModelToCustomer(CustomerModel customerModel) {
        return new Customer(new CustomerId(UUID.fromString(customerModel.getId())),
                customerModel.getUsername(),
                customerModel.getFirstName(),
                customerModel.getLastName());
    }

    private List<OrderItem> orderItemsToOrderItemEntities(
            List<com.sanzee.ecom.system.order.service.domain.dto.create.OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem ->
                        OrderItem.builder()
                                .product(Product.builder().id(new ProductId(orderItem.getProductId())).build())
                                .price(new Money(orderItem.getPrice()))
                                .quantity(orderItem.getQuantity())
                                .subTotal(new Money(orderItem.getSubTotal()))
                                .build()).collect(Collectors.toList());
    }

    private StreetAddress orderAddressToStreetAddress(OrderAddress orderAddress) {
        return new StreetAddress(
                UUID.randomUUID(),
                orderAddress.getStreet(),
                orderAddress.getPostalCode(),
                orderAddress.getCity()
        );
    }

}
