package com.example.eventuser.mapper;

import com.example.eventuser.dto.response.UserGameResponse;
import com.example.eventuser.entity.UserGame;
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
