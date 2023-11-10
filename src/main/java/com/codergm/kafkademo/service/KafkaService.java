package com.codergm.kafkademo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${kafka.topics.topic1}")
    private String topic1;

    @Value(value = "${kafka.topics.topic2}")
    private String topic2;

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        processMessage(topic1, message);
        processMessage(topic2, message);
    }

    private void processMessage(String topic, String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(success ->
                        System.out.println("Sent message=[" + message + "] to topic["+topic+"]" +
                                " with offset=[" + success.getRecordMetadata().offset() + "]")
                , failure ->
                        System.out.println("Unable to send message=[" + message + "] due to " + failure.getMessage())
        );
    }
}
