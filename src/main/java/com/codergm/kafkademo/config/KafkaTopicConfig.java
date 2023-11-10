package com.codergm.kafkademo.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.admin.AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value ="${kafka.topics.topic1}")
    private String topic1;

    @Value(value ="${kafka.topics.topic2}")
    private String topic2;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> config = new HashMap<>();
        config.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(config);
    }


    @Bean
    public NewTopic topic1() {
        return new NewTopic(topic1, 1, (short) 1);
    }
    @Bean
    public NewTopic topic2(){
        return new NewTopic(topic2,1,(short) 1);
    }
}
