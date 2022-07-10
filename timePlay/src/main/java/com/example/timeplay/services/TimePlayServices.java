package com.example.timeplay.services;
import com.example.eventuser.repository.UserGameRepository;
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
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class TimePlayServices {
    private final UserGameRepository userGameRepository;
    private final PushEventServices pushEventServices;
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
        assert lastTime != null;
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
        int cacheTimePlay = redisServices.getValueRedisByKey("timePlay") == null ? timePlay : (int) redisServices.getValueRedisByKey("timePlay") + timePlay;
        redisServices.setRedis("timePlay", cacheTimePlay);
        if (cacheTimePlay > 100) {
            PointEvent pointEvent = new PointEvent();
            pointEvent.setTimePlay((long) cacheTimePlay);
            pointEvent.setUserGameId((payload.getUserGameId()));
            pushEventServices.sendMessage("point-topic", pointEvent);
            redisServices.setRedis("timePlay", 0);
            userGameRepository.updateUserGame(payload.getEventId(), cacheTimePlay + payload.getCurrentTimePlay(), payload.getUserGameId());
        } else  {
            redisServices.setRedis("timePlay", cacheTimePlay);
        }
    }

}
