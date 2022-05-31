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
    @NotEmpty
    private String status;
    @NotNull
    private LocalDateTime time;
    @NotNull
    private Long gameId;
    @NotNull
    private Long userId;
}
