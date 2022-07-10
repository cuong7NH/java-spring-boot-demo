package com.example.pointplay.services;

import com.example.eventuser.repository.UserGameRepository;

import com.example.kafka.configuration.PointEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
@Slf4j
public class PointPlayServices {
    private final UserGameRepository userGameRepository;

    @KafkaListener(topics = "point-topic", groupId = "tpd-loggers", clientIdPrefix = "json",
            containerFactory = "kafkaListenerContainerFactory")
    public void listenAsObject(ConsumerRecord<String, PointEvent> cr,
                               @Payload PointEvent pointEvent) {
        Long playTime = pointEvent.getTimePlay();
        if (playTime == 0) {
            return;
        }
        int phanNg = (int) (playTime / 10);
        userGameRepository.updatePlayPointById((long) phanNg, pointEvent.getUserGameId());
    }

}
