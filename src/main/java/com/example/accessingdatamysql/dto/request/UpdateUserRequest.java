package com.example.accessingdatamysql.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    private String password;
    private String fullName;
}
