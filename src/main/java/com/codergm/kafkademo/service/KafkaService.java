package com.codergm.kafkademo.service;

import com.codergm.kafkademo.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private final KafkaTemplate<String, Greeting> kafkaObjectTemplate;

    @Value(value = "${kafka.topics.topic1}")
    private String topic1;

    @Value(value = "${kafka.topics.topic2}")
    private String topic2;

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate,
                        @Qualifier(value = "kafkaObjectTemplate") KafkaTemplate<String, Greeting> kafkaObjectTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaObjectTemplate = kafkaObjectTemplate;
    }

    public void sendMessage(String message) {
        processMessage(topic1, message);
        processMessage(topic2, message);
    }

    public void sendGreeting(Greeting greeting){
        processGreeting(topic1,greeting);
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

    private void processGreeting(String topic, Greeting greeting) {
        ListenableFuture<SendResult<String, Greeting>> future = kafkaObjectTemplate.send(topic, greeting);
        future.addCallback(success ->
                        System.out.println("Sent greeting message=[" + greeting.getName() + "] " +
                                "and greeting message=["+greeting.getMsg()+"]to topic["+topic+"]" +
                                " with offset=[" + success.getRecordMetadata().offset() + "]")
                , failure ->
                        System.out.println("Unable to send message=[" + greeting + "] due to " + failure.getMessage())
        );
    }

}
