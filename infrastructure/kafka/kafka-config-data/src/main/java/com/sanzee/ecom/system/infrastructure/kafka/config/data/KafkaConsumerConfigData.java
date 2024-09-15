package com.sanzee.ecom.system.infrastructure.kafka.config.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data // Lombok annotation that automatically generates getter, setter, toString, equals, and hashCode methods.
@Configuration // Marks this class as a Spring @Configuration, indicating that it declares one or more @Bean methods.
@ConfigurationProperties(prefix = "kafka-consumer-config") // Binds properties with the prefix "kafka-consumer-config" from external configuration (e.g., application.yml) to this class.
public class KafkaConsumerConfigData {

    // The fully qualified class name of the key deserializer, used for deserializing the key of the Kafka message.
    private String keyDeserializer;

    // The fully qualified class name of the value deserializer, used for deserializing the value of the Kafka message.
    private String valueDeserializer;

    // Defines how the consumer should behave if no initial offset is found. Common values: "earliest", "latest", or "none".
    private String autoOffsetReset;

    // Key for enabling specific Avro reader for deserializing Kafka keys when Avro serialization is used.
    private String specificAvroReaderKey;

    // Key for enabling specific Avro reader for deserializing Kafka values when Avro serialization is used.
    private String specificAvroReader;

    // Flag to indicate whether the consumer should handle messages in batches, allowing multiple messages to be processed at once.
    private Boolean batchListener;

    // Whether the Kafka consumer should start automatically when the Spring application context is initialized.
    private Boolean autoStartup;

    // The number of threads (consumers) handling message consumption in parallel, defining the concurrency level.
    private Integer concurrencyLevel;

    // The maximum amount of time in milliseconds a consumer can be considered alive without receiving heartbeats from Kafka.
    private Integer sessionTimeoutMs;

    // The interval in milliseconds between heartbeats sent by the consumer to the Kafka broker, helping detect failures.
    private Integer heartbeatIntervalMs;

    // Maximum interval in milliseconds between poll() calls before the consumer is considered failed and kicked out of the group.
    private Integer maxPollIntervalMs;

    // The maximum time in milliseconds a consumer will wait while polling messages from Kafka.
    private Long pollTimeoutMs;

    // The maximum number of records that the consumer will fetch in a single poll.
    private Integer maxPollRecords;

    // Default setting for the maximum amount of data in bytes that the consumer will fetch from a partition.
    private Integer maxPartitionFetchBytesDefault;

    // Boost factor for increasing the max partition fetch bytes, useful in high-throughput scenarios.
    private Integer maxPartitionFetchBytesBoostFactor;
}

