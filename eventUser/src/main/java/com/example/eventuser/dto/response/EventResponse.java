package com.example.eventuser.dto.response;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)

public class EventResponse {
    private Long id;
    private String status;
    private Date time;
    private UserGameResponse userGame;
}
