package com.example.eventuser.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UserGameRequest extends PagingDataRequest {
    private Long id;
    private Long userId;
    private String username;
}
