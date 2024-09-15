package com.sanzee.ecom.system.infrastructure.kafka.config.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data // Lombok annotation that automatically generates getter, setter, toString, equals, and hashCode methods.
@Configuration // Marks this class as a Spring @Configuration, indicating that it declares one or more @Bean methods.
@ConfigurationProperties(prefix = "kafka-producer-config") // Binds properties with the prefix "kafka-producer-config" from external configuration (e.g., application.yml) to this class.
public class KafkaProducerConfigData {

    // The fully qualified class name of the key serializer, used for serializing the key of the Kafka message before sending.
    private String keySerializerClass;

    // The fully qualified class name of the value serializer, used for serializing the value of the Kafka message before sending.
    private String valueSerializerClass;

    // The compression type to be used for Kafka messages, such as "none", "gzip", "snappy", or "lz4" to reduce data size.
    private String compressionType;

    // Defines the number of acknowledgments the producer requires from Kafka before considering a message as successfully sent. Common values: "all", "1", "0".
    private String acks;

    // The size in bytes of each batch that the producer will send. Larger batches improve throughput but increase latency.
    private Integer batchSize;

    // A boost factor for dynamically increasing the batch size based on throughput requirements and performance optimizations.
    private Integer batchSizeBoostFactor;

    // Time in milliseconds to wait before sending a batch of messages, even if the batch size isn't reached. Useful to improve latency.
    private Integer lingerMs;

    // Time in milliseconds that the producer will wait for a response from the Kafka broker before considering a request as failed.
    private Integer requestTimeoutMs;

    // The number of retries the producer will attempt when a send request fails due to transient errors.
    private Integer retryCount;
}