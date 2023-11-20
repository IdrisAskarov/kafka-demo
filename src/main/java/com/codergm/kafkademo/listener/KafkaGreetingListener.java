package com.codergm.kafkademo.listener;

import com.codergm.kafkademo.model.Greeting;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaGreetingListener {

    @KafkaListener(groupId = "greeting", topics = "topic3", containerFactory = "greetingKafkaListenerContainerFactory")
    public void barListner(Greeting greeting) {
        System.out.println("received greeting object " + greeting);
    }
}
