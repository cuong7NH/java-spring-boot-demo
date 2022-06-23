package com.example.eventuser.dto.response;

import java.time.LocalDateTime;

public interface EventResponse2 {
    Long getId();
    String getStatus();
    LocalDateTime getTime();
    String getGameName();
    String getUsername();

}

