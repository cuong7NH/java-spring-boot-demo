package com.example.accessingdatamysql.dto.response;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)

public class UserGameResponse {
    private Long id;
    private Long userId;
    private GameResponse game;
    private Long playTime;
    private Long lastEventId;
}
