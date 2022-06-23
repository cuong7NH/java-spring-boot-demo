package com.example.kafka.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PushEventServices {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String topic, Object rq) {
        System.out.println("Pushed Event to topic " + topic + " , message: " + rq.toString());
        kafkaTemplate.send(topic, rq);
    }

}
