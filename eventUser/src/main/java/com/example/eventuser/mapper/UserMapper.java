package com.example.eventuser.mapper;

import com.example.eventuser.dto.response.UserResponse;
import com.example.eventuser.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserResponse userToUserResponse(User user);
    List<UserResponse> userToUserResponse(List<User> user);
}
