//package com.example.kafka.configuration;
//
//import com.example.kafka.entity.TimeEvent;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.event.KafkaEvent;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//import java.util.HashMap;
//import java.util.Map;
//@EnableKafka
//@Configuration
//public class ConsumerConfiguration {
//    @Value("${kafka.broker}")
//    private String KAFKA_BROKER;
//    @Value("${kafka.groupId}")
//    private String GROUP_ID ;
//
//    @Bean
//    public ConsumerFactory<String, Object> consumingEventSendKafkaMessage() {
//        Map<String, Object> configMap = new HashMap<>();
//        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
//        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        configMap.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
//        configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example.kafka.entity");
//        return new DefaultKafkaConsumerFactory<>(configMap);
//    }
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, TimeEvent> listenerEventSendTimeEventMessage() {
//        ConcurrentKafkaListenerContainerFactory<String, TimeEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumingEventSendKafkaMessage());
//        return factory;
//    }
//}
