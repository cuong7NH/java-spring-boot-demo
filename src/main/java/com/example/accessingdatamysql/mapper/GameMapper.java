package com.example.accessingdatamysql.mapper;

import com.example.accessingdatamysql.dto.response.GameResponse;
import com.example.accessingdatamysql.entity.Game;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface GameMapper {
    GameResponse gameToGameResponse(Game game);
    List<GameResponse> gameToGameResponse(List<Game> games);
}
