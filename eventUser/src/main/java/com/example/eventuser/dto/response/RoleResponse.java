
package com.example.eventuser.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class RoleResponse {
    private Long id;
    private String name;
}
