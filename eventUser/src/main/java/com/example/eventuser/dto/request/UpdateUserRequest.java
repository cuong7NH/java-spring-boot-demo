package com.example.eventuser.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UpdateUserRequest {
    @NotEmpty
    private String fullName;
}
