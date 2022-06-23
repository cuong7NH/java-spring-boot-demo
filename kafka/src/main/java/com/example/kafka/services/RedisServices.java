package com.example.kafka.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisServices {
    @Autowired
    private RedisTemplate template;

    public void setRedis(String key, Object value) {
        template.opsForValue().set(key, value);
    }
    public void setLongRedis(String key, Long value) {
        template.opsForValue().set(key, value);
    }
    public Object getValueRedisByKey(String key) {
        return template.opsForValue().get(key);
    }
}
