package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.request.UserGameRequest;
import com.example.accessingdatamysql.dto.response.UserGameResponse;
import com.example.accessingdatamysql.entity.UserGame;
import com.example.accessingdatamysql.repository.UserGameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserGameService {

    private final UserGameRepository userGameRepository;
    public Page<UserGame> findUserGame(UserGameRequest request) {
        var pageSize = PageRequest.of(request.getPage(), request.getSize());
        return userGameRepository.filter(request.getId(), request.getUserId(), request.getUsername(), pageSize);
    }

    Optional<UserGame> findById(Long id){
        return userGameRepository.findById(id);
    }

//    public UserGame save(UserGame userGame){
//        return userGameRepository.save(userGame);
//    }
//
    public int updateUserGame(Long lastEventId,  Long playTime, Long userGameId) {
        return userGameRepository.updateUserGame(lastEventId, playTime, userGameId);
    }
}
