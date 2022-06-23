package com.example.eventuser.mapper;

import com.example.eventuser.dto.response.GameResponse;
import com.example.eventuser.entity.Game;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface GameMapper {
    GameResponse gameToGameResponse(Game game);
    List<GameResponse> gameToGameResponse(List<Game> games);
}
