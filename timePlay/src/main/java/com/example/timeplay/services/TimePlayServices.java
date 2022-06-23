package com.example.timeplay.services;

import com.example.eventuser.entity.Event;
import com.example.eventuser.exception.NotFoundRecordException;
import com.example.eventuser.repository.EventRepository;
import com.example.eventuser.repository.UserGameRepository;
import com.example.eventuser.service.EventService;
import com.example.kafka.configuration.PointEvent;
import com.example.kafka.configuration.TimeEvent;
import com.example.kafka.services.PushEventServices;
import com.example.kafka.services.RedisServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TimePlayServices {
    private final UserGameRepository userGameRepository;
    private final PushEventServices pushEventServices;
    private final EventRepository eventRepository;
    private final EventService eventService;
    private final RedisServices redisServices;
    private long getDiffInTimeBetweenEvent(TimeEvent timeEvent) {
        Long lastEventId = (Long) redisServices.getValueRedisByKey("lastEventId");
        String lastEventTime = (String) redisServices.getValueRedisByKey("lastEventTime");
        LocalDateTime lastTime = lastEventTime == null ? null : LocalDateTime.parse((CharSequence) redisServices.getValueRedisByKey("lastEventTime"), DateTimeFormatter.ISO_DATE_TIME);
        String lastStatus = (String) redisServices.getValueRedisByKey("lastEventStatus");
        redisServices.setRedis("lastEventId", timeEvent.getEventId());
        redisServices.setRedis("lastEventStatus", timeEvent.getStatus());
        redisServices.setRedis("lastEventTime", timeEvent.getRealTime().format(DateTimeFormatter.ISO_DATE_TIME));
        if (!Objects.equals(timeEvent.getStatus(), "PLAYING") || lastEventId == null || !Objects.equals(lastStatus, "PLAYING")) {
            return 0;
        }
        var diffInTime = ChronoUnit.SECONDS.between(lastTime, timeEvent.getRealTime());
        System.out.println("diffInTime" + diffInTime);
        return diffInTime > 60 ? 0 : diffInTime;
    }


    @KafkaListener(topics = "time-topic", groupId = "tpd-loggers", clientIdPrefix = "json",
            containerFactory = "kafkaListenerContainerFactory")
    public void listenAsObject(ConsumerRecord<String, TimeEvent> cr,
                               @Payload TimeEvent payload) {
        System.out.println("payload" + payload.toString());
        int timePlay = (int)getDiffInTimeBetweenEvent(payload);
        PointEvent pointEvent = new PointEvent();
        pointEvent.setTimePlay((long) timePlay);
        pointEvent.setUserGameId((payload.getUserGameId()));
        pushEventServices.sendMessage("point-topic", pointEvent);
        System.out.println("timePlay" + timePlay);
        userGameRepository.updateUserGame(payload.getEventId(), timePlay + payload.getCurrentTimePlay(), payload.getUserGameId());
//        int cacheTimePlay = redisServices.getValueRedisByKey("timePlay") == null ? 0 : (int) redisServices.getValueRedisByKey("timePlay");
//        System.out.println("cacheTimePlay" + cacheTimePlay);
//        redisServices.setRedis("timePlay", cacheTimePlay + timePlay);
//        if (cacheTimePlay > 100) {
//            redisServices.setRedis("timePlay", 0);
//            userGameRepository.updateUserGame(payload.getEventId(), timePlay + payload.getCurrentTimePlay(), payload.getUserGameId());
//        }
//        System.out.println("cacheTimePlay"+ cacheTimePlay);
//        if (timePlay < 60) {
//
//        }
//        System.out.println("timePlay" + timePlay);
    }

}
