package com.codergm.kafkademo.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMiscListener {

    @KafkaListener(topics = "codergm", groupId = "misc", containerFactory = "miscFilterKafkaListenerContainerFactory")
    public void listenWithFilter(String msg){
        System.out.println("Misc group message " + msg);
    }
}
