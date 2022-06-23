package com.example.timeplay.config;
import com.example.kafka.configuration.TimeEvent;
import com.example.timeplay.services.TimePlayServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

@EnableKafka
@Configuration
@RequiredArgsConstructor
@Slf4j
public class TimePlayConfig {
    private final TimePlayServices timePlayServices;
//    @KafkaListener(topics = "time-topic", groupId = "tpd-loggers", clientIdPrefix = "string",
//            containerFactory = "kafkaListenerStringContainerFactory")
//    public void listenString(ConsumerRecord<String, String> cr,
//                               @Payload String payload) throws JsonProcessingException {
//        System.out.println("vvvvvvvvvvvv " + payload +  " 123 " +  cr);
//
//    }

//    @KafkaListener(topics = "time-topic", groupId = "tpd-loggers", clientIdPrefix = "json",
//            containerFactory = "kafkaListenerContainerFactory")
//    public void listenAsObject(ConsumerRecord<String, TimeEvent> cr,
//                               @Payload TimeEvent payload) {
//        System.out.println("message:   " + payload.getStatus() + "  11111  " + payload + "  sss " + payload.getRealTime() + cr);
//    }

//    @KafkaListener(topics = "time-topic-hahaha", groupId = "cuongnh-group", containerFactory = "listenerEventSendTimeEventMessage")
//    public void userEventListener(TimeEvent timeEvent) {
//        try {
//            log.info("Receive event {}", timeEvent.getStatus());
////            bitStarService.reward(eventMessage, configProvider.getIdleEventRequestTimeout() * 1000L, event -> {
////                // send event to event db topic after calculate reward
////                log.info("Send to db {}", eventMessage.getVisitorId());
////                eventPublisher.publishEvent(event);
////            }); // 60000 is tmp replace it
//        } catch (Exception exception) {
//            //send to analistic server elasticsearch in the fureture
//            log.error("Failed to resolve event", exception);
//            exception.printStackTrace();
//        }
//    }
}
