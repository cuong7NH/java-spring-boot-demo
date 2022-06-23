package com.example.kafka.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter

public class PointEvent {

    @JsonProperty("userGameId")
    private Long userGameId;
    @JsonProperty("timePlay")
    private Long timePlay;

    public PointEvent() {
    }

    @Override
    public String toString() {
        return "PointEvent{" +
                "userGameId=" + userGameId +
                ", timePlay=" + timePlay +
                '}';
    }
}
