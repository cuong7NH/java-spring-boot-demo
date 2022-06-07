package com.example.accessingdatamysql.dto.response;
import com.example.accessingdatamysql.entity.Game;
import com.example.accessingdatamysql.entity.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

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
