package com.example.accessingdatamysql.dto.response;
import com.example.accessingdatamysql.entity.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)

public class UserResponse {
    private Integer id;
    private String fullName;
    private String username;
    private List<Role> roles;
}
