package com.sanzee.ecom.system.restaurant.service.messaging.publisher.kafka;

import com.sanzee.ecom.system.kafka.order.avro.model.RestaurantApprovalResponseAvroModel;
import com.sanzee.ecom.system.kafka.producer.KafkaMessageHelper;
import com.sanzee.ecom.system.kafka.producer.service.KafkaProducer;
import com.sanzee.ecom.system.restaurant.service.domain.config.RestaurantServiceConfigData;
import com.sanzee.ecom.system.restaurant.service.domain.event.OrderApprovedEvent;
import com.sanzee.ecom.system.restaurant.service.domain.event.OrderRejectedEvent;
import com.sanzee.ecom.system.restaurant.service.domain.ports.output.message.publisher.OrderApprovedMessagePublisher;
import com.sanzee.ecom.system.restaurant.service.domain.ports.output.message.publisher.OrderRejectedMessagePublisher;
import com.sanzee.ecom.system.restaurant.service.messaging.mapper.RestaurantMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderRejectedKafkaMessagePublisher implements OrderRejectedMessagePublisher {

    private final RestaurantMessagingDataMapper restaurantMessagingDataMapper;
    private final KafkaProducer<String, RestaurantApprovalResponseAvroModel> kafkaProducer;
    private final RestaurantServiceConfigData restaurantServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;

    public OrderRejectedKafkaMessagePublisher(RestaurantMessagingDataMapper restaurantMessagingDataMapper,
                                              KafkaProducer<String, RestaurantApprovalResponseAvroModel> kafkaProducer,
                                              RestaurantServiceConfigData restaurantServiceConfigData,
                                              KafkaMessageHelper kafkaMessageHelper) {
        this.restaurantMessagingDataMapper = restaurantMessagingDataMapper;
        this.kafkaProducer = kafkaProducer;
        this.restaurantServiceConfigData = restaurantServiceConfigData;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    public void publish(OrderRejectedEvent orderRejectedEvent) {
        String orderId = orderRejectedEvent.getOrderApproval().getOrderId().getValue().toString();

        log.info("Received OrderRejectedEvent for order id: {}", orderId);

        try {
            RestaurantApprovalResponseAvroModel restaurantApprovalResponseAvroModel = restaurantMessagingDataMapper
                    .orderApprovalEventToRestaurantApprovalResponseAvroMode(orderRejectedEvent);

            kafkaProducer.send(
                    restaurantServiceConfigData.getRestaurantApprovalResponseTopicName(),
                    orderId,
                    restaurantApprovalResponseAvroModel,
                    kafkaMessageHelper.getKafkaCallback(restaurantServiceConfigData.getRestaurantApprovalResponseTopicName(),
                            restaurantApprovalResponseAvroModel, orderId, "RestaurantApprovalResponseAvroModel")
                    );
            log.info("RestaurantApprovalResponseAvroModel sent to kafka at: {}", System.nanoTime());
        } catch (Exception e) {
            log.error("Error while sending RestaurantApprovalResponseAvroModel message to kafka with order id: {}, error: {}",
                    orderId, e.getMessage());
        }
    }
}
