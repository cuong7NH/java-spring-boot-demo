package com.example.accessingdatamysql.mapper;

import com.example.accessingdatamysql.dto.response.UserGameResponse;
import com.example.accessingdatamysql.dto.response.UserResponse;
import com.example.accessingdatamysql.entity.User;
import com.example.accessingdatamysql.entity.UserGame;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface UserGameMapper {
    @Mapping(source = "user.id", target = "userId")
    UserGameResponse userGameToUserGameResponse(UserGame userGame);
    @Mapping(source = "user.id", target = "userId")
    List<UserGameResponse> userGameToUserGameResponse(List<UserGame> userGame);
}
