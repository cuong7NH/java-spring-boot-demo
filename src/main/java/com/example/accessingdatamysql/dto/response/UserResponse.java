package com.example.accessingdatamysql.dto.response;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)

public class UserResponse {
    private Integer id;
    private String fullName;
    private String username;
}
