package com.example.accessingdatamysql.mapper;

import com.example.accessingdatamysql.dto.response.UserResponse;
import com.example.accessingdatamysql.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserResponse userToUserResponse(User user);
    List<UserResponse> userToUserResponse(List<User> user);
}
