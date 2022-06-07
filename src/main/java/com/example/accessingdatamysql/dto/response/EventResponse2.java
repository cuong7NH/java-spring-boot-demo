package com.example.accessingdatamysql.dto.response;

import com.example.accessingdatamysql.entity.Game;
import com.example.accessingdatamysql.entity.User;

import java.time.LocalDateTime;

public interface EventResponse2 {
    Long getId();
    String getStatus();
    LocalDateTime getTime();
    String getGameName();
    String getUsername();

}

