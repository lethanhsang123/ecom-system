package com.sanzee.ecom.system.restaurant.service.messaging.mapper;

import com.sanzee.ecom.system.domain.valueobject.ProductId;
import com.sanzee.ecom.system.domain.valueobject.RestaurantOrderStatus;
import com.sanzee.ecom.system.kafka.order.avro.model.OrderApprovalStatus;
import com.sanzee.ecom.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel;
import com.sanzee.ecom.system.kafka.order.avro.model.RestaurantApprovalResponseAvroModel;
import com.sanzee.ecom.system.restaurant.service.domain.dto.RestaurantApprovalRequest;
import com.sanzee.ecom.system.restaurant.service.domain.entity.Product;
import com.sanzee.ecom.system.restaurant.service.domain.event.OrderApprovalEvent;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RestaurantMessagingDataMapper {

    public RestaurantApprovalResponseAvroModel orderApprovalEventToRestaurantApprovalResponseAvroMode(
            OrderApprovalEvent orderApprovalEvent) {
        return RestaurantApprovalResponseAvroModel.newBuilder()
                .setId(UUID.randomUUID())
                .setSagaId(UUID.fromString(Strings.EMPTY))
                .setOrderId(orderApprovalEvent.getOrderApproval().getOrderId().getValue())
                .setRestaurantId(orderApprovalEvent.getRestaurantId().getValue())
                .setCreatedAt(orderApprovalEvent.getCreatedAt().toInstant())
                .setOrderApprovalStatus(OrderApprovalStatus.valueOf(
                        orderApprovalEvent.getOrderApproval().getApprovalStatus().name()))
                .setFailureMessages(orderApprovalEvent.getFailureMessages())
                .build();
    }

    public RestaurantApprovalRequest restaurantApprovalRequestAvroModelToRestaurantApproval(
            RestaurantApprovalRequestAvroModel restaurantApprovalRequestAvroModel) {
        return RestaurantApprovalRequest.builder()
                .id(restaurantApprovalRequestAvroModel.getId().toString())
                .sagaId(restaurantApprovalRequestAvroModel.getSagaId().toString())
                .restaurantId(restaurantApprovalRequestAvroModel.getRestaurantId().toString())
                .orderId(restaurantApprovalRequestAvroModel.getOrderId().toString())
                .restaurantOrderStatus(RestaurantOrderStatus.valueOf(restaurantApprovalRequestAvroModel
                        .getRestaurantOrderStatus().name()))
                .products(restaurantApprovalRequestAvroModel.getProducts()
                        .stream().map(avroModel ->
                                Product.builder()
                                        .productId(new ProductId(UUID.fromString(avroModel.getId())))
                                        .quantity(avroModel.getQuantity())
                                        .build())
                        .collect(Collectors.toList()))
                .price(restaurantApprovalRequestAvroModel.getPrice())
                .createdAt(restaurantApprovalRequestAvroModel.getCreatedAt())
                .build();
    }
}
