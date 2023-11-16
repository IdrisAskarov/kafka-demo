package com.codergm.kafkademo.config;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

public interface KafkaProducerConfig<K,V> {

    ProducerFactory<K, V> producerFactory();

    KafkaTemplate<K, V> kafkaTemplate();
}
