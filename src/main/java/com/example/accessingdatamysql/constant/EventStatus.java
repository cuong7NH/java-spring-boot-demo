package com.example.accessingdatamysql.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

@Getter
@ToString
@AllArgsConstructor
public enum EventStatus {
    START(0), PLAYING(1), END(2);
    private Integer key;

    public static EventStatus of(Integer key) {
        return Arrays.stream(EventStatus.values()).filter(userStatus -> userStatus.key.equals(key))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public static Boolean exist(Integer key) {
        return Arrays.stream(EventStatus.values()).anyMatch(userStatus -> userStatus.key.equals(key));
    }
}
