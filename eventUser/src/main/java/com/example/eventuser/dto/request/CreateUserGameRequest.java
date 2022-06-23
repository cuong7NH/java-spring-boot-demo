package com.example.eventuser.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateUserGameRequest {
    @NotNull
    private Long GameId;
}
