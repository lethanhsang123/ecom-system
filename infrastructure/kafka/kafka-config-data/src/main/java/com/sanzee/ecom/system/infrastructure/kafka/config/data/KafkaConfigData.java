package com.sanzee.ecom.system.infrastructure.kafka.config.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data // Lombok annotation that automatically generates getter, setter, toString, equals, and hashCode methods.
@Configuration // Marks this class as a Spring @Configuration, indicating that it declares one or more @Bean methods.
@ConfigurationProperties(prefix = "kafka-config") // Binds properties with the prefix "kafka-config" from external configuration (e.g., application.yml) to this class.
public class KafkaConfigData {

    // Kafka cluster's bootstrap servers, a list of host:port pairs for connecting to Kafka brokers.
    private String bootstrapServers;

    // The key used to access the schema registry in Kafka, which stores schemas for messages.
    private String schemaRegistryUrlKey;

    // URL of the schema registry where Avro or other serialization schemas are stored.
    private String schemaRegistryUrl;

    // Number of partitions for the Kafka topic, determines parallelism and data distribution.
    private Integer numOfPartitions;

    // Replication factor for Kafka topics, indicating the number of replicas of each partition.
    private Short replicationFactor;
}

