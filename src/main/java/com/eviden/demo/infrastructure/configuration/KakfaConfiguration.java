/* EVIDEN (C)2024 */
package com.eviden.demo.infrastructure.configuration;

import com.eviden.demo.domain.avro.PurchaseOrderAvro;

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KakfaConfiguration {

    @Value(value = "${spring.kafka.bootstrap-server}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.schema-registry-server}")
    private String schemaRegistryAddress;

    @Bean
    KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);

        return new KafkaAdmin(configs);
    }

    // Producers
    @Bean
    KafkaTemplate<String, PurchaseOrderAvro> kafkaPurchaseOrderAvroTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(configureProducers()));
    }

    // Consumers
    @Bean
    ConcurrentKafkaListenerContainerFactory<String, PurchaseOrderAvro>
            kafkaListenerContainerFactoryForOrderPurchase() {
        ConcurrentKafkaListenerContainerFactory<String, PurchaseOrderAvro> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory("group-consumer-order-purchase"));

        return factory;
    }

    // Topics
    @Bean
    NewTopic eventTopic() {
        return TopicBuilder.name("v1.public.eviden.event-producer")
                .partitions(1)
                .replicas(1)
                .build();
    }

    private Map<String, Object> configureProducers() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        configProps.put(
                AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryAddress);

        return configProps;
    }

    private <T> ConsumerFactory<String, T> consumerFactory(String groupId) {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configProps.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
        configProps.put(
                AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryAddress);

        return new DefaultKafkaConsumerFactory<>(configProps);
    }
}
