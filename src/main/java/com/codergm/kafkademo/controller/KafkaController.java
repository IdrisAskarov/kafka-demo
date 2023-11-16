package com.codergm.kafkademo.controller;

import com.codergm.kafkademo.model.Greeting;
import com.codergm.kafkademo.service.KafkaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    private final KafkaService kafkaService;

    public KafkaController(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @PostMapping("/topic1")
    public void sendMessage(@RequestParam("msg") String msg){
        kafkaService.sendMessage(msg);
    }

    @PostMapping("/topic2")
    public void sendGreeting(@RequestBody Greeting greeting){
        kafkaService.sendGreeting(greeting);
    }
}
