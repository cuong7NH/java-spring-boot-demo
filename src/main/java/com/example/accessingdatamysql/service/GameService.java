package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.request.GameRequest;
import com.example.accessingdatamysql.entity.Game;
import com.example.accessingdatamysql.entity.User;
import com.example.accessingdatamysql.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    public Page<Game> findGames(GameRequest request) {
        var pageSize = PageRequest.of(request.getPage(), request.getSize());
        return gameRepository.filter(request.getId(), pageSize);
    }
    public Optional<Game> findById(Long Id) {
        return gameRepository.findById(Id);
    }

}
