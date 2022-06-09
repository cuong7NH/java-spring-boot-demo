package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.request.CreateUserGameRequest;
import com.example.accessingdatamysql.dto.request.UserGameRequest;
import com.example.accessingdatamysql.dto.response.UserGameResponse;
import com.example.accessingdatamysql.entity.Game;
import com.example.accessingdatamysql.entity.User;
import com.example.accessingdatamysql.entity.UserGame;
import com.example.accessingdatamysql.repository.GameRepository;
import com.example.accessingdatamysql.repository.UserGameRepository;
import com.example.accessingdatamysql.repository.UserRepository;
import com.example.accessingdatamysql.security.jwt.AuthTokenFilter;
import com.example.accessingdatamysql.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserGameService {

    private final UserGameRepository userGameRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;


    private  final AuthTokenFilter authTokenFilter;
    private final JwtUtils jwtUtils;
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

    public UserGame saveUserGame(HttpServletRequest request, CreateUserGameRequest req) {
        String jwt = authTokenFilter.parseJwt(request);
        String userId = jwtUtils.getUserIdFromJwtToken(jwt);

        User user = userRepository.findById(Long.valueOf(userId)).orElseThrow();
        Game game = gameRepository.findById(req.getGameId()).orElseThrow();
        if(userGameRepository.existsByUserAndGame(user, game)) {
            throw new RuntimeException();
        }
        UserGame userGame = new UserGame();
        userGame.setGame(game);
        userGame.setPlayTime(0L);
        userGame.setUser(user);
        return userGameRepository.save(userGame);
    }
}
