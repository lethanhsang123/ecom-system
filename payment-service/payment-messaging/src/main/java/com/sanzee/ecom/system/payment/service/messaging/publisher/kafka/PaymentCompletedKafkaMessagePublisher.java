package com.sanzee.ecom.system.payment.service.messaging.publisher.kafka;

import com.sanze.ecom.system.payment.service.domain.event.PaymentCompletedEvent;
import com.sanzee.ecom.system.kafka.order.avro.model.PaymentResponseAvroModel;
import com.sanzee.ecom.system.kafka.producer.KafkaMessageHelper;
import com.sanzee.ecom.system.kafka.producer.service.KafkaProducer;
import com.sanzee.ecom.system.payment.service.domain.config.PaymentServiceConfigData;
import com.sanzee.ecom.system.payment.service.domain.ports.output.message.publisher.PaymentCompletedMessagePublisher;
import com.sanzee.ecom.system.payment.service.messaging.mapper.PaymentMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentCompletedKafkaMessagePublisher implements PaymentCompletedMessagePublisher {

    private final PaymentMessagingDataMapper paymentMessagingDataMapper;
    private final KafkaProducer<String, PaymentResponseAvroModel> kafkaProducer;
    private final PaymentServiceConfigData paymentServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;

    public PaymentCompletedKafkaMessagePublisher(PaymentMessagingDataMapper paymentMessagingDataMapper,
                                                 KafkaProducer<String, PaymentResponseAvroModel> kafkaProducer,
                                                 PaymentServiceConfigData paymentServiceConfigData,
                                                 KafkaMessageHelper kafkaMessageHelper) {
        this.paymentMessagingDataMapper = paymentMessagingDataMapper;
        this.kafkaProducer = kafkaProducer;
        this.paymentServiceConfigData = paymentServiceConfigData;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    public void publish(PaymentCompletedEvent domainEvent) {
        String orderId = domainEvent.getPayment().getOrderId().getValue().toString();

        log.info("Received PaymentCompletedEvent for order id: {}", orderId);

        PaymentResponseAvroModel paymentResponseAvroModel = paymentMessagingDataMapper.paymentEventToPaymentResponseAvroModel(domainEvent);

        kafkaProducer.send(paymentServiceConfigData.getPaymentResponseTopicName(), orderId, paymentResponseAvroModel,
                kafkaMessageHelper.getKafkaCallback(
                        paymentServiceConfigData.getPaymentResponseTopicName(),
                        paymentResponseAvroModel,
                        orderId,
                        "PaymentResponseAvroModel")
        );

        log.info("PaymentResponseAvroModel sent to kafka for order id: {}", orderId);
    }

}
