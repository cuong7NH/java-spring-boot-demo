package com.example.accessingdatamysql.dto.request;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreateEventRequest {
    @NotNull
    private String status;
    @NotNull
    private LocalDateTime time;
    @NotNull
    private Long userGameId;
}
