package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.request.CreateGameRequest;
import com.example.accessingdatamysql.dto.request.GameRequest;
import com.example.accessingdatamysql.dto.response.GameCountEventResponse;
import com.example.accessingdatamysql.dto.response.GameResponse2;
import com.example.accessingdatamysql.entity.Game;
import com.example.accessingdatamysql.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {
    private final GameRepository gameRepository;
    public Page<GameResponse2> findGames(GameRequest request) {
        var pageSize = PageRequest.of(request.getPage(), request.getSize());
        return gameRepository.filter(request.getId(), pageSize);
    }

    public Optional<Game> findById(Long Id) {
        return gameRepository.findById(Id);
    }
    public Game addGame(CreateGameRequest rq) {
        Game game = new Game();
        game.setName(rq.getName());
        return gameRepository.save(game);
    }
    public List<GameCountEventResponse> getGameListCountEvent(){
        return  gameRepository.getGameListCountEvent();
    };
}
