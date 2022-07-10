package com.example.kafka.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter

public class TimeEvent {
    @JsonProperty("status")
    private String status;
    @JsonProperty("realTime")
    private LocalDateTime realTime;
    @JsonProperty("eventId")
    private Long eventId;
    @JsonProperty("userGameId")
    private Long userGameId;
    @JsonProperty("currentTimePlay")
    private Long currentTimePlay;

    public TimeEvent() {
    }

    @Override
    public String toString() {
        return "TimeEvent{" +
                "status='" + status + '\'' +
                ", realTime=" + realTime +
                ", eventId=" + eventId +
                ", userGameId=" + userGameId +
                ", currentTimePlay=" + currentTimePlay +
                '}';
    }
}
