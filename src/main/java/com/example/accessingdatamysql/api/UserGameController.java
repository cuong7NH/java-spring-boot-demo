package com.example.accessingdatamysql.api;

import com.example.accessingdatamysql.dto.PagingResponse;
import com.example.accessingdatamysql.dto.request.UserGameRequest;
import com.example.accessingdatamysql.dto.response.UserGameResponse;
import com.example.accessingdatamysql.mapper.UserGameMapper;
import com.example.accessingdatamysql.security.jwt.AuthTokenFilter;
import com.example.accessingdatamysql.security.jwt.JwtUtils;
import com.example.accessingdatamysql.service.UserGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserGameController {
    private final UserGameService service;
    private final UserGameMapper userGameMapper;
    private  final AuthTokenFilter authTokenFilter;
    private final JwtUtils jwtUtils;
    @GetMapping("/user_game")
    public ResponseEntity<PagingResponse<UserGameResponse>> getUserGame(UserGameRequest req) {
        var page = service.findUserGame(req);
        var rs = new PagingResponse<UserGameResponse>()
                .setTotal((int) page.getTotalElements())
                .setData(userGameMapper.userGameToUserGameResponse(page.getContent()));
        return ResponseEntity.ok(rs);
    }
    @GetMapping("/user_game/me")
    public ResponseEntity<PagingResponse<UserGameResponse>> getMyGame(UserGameRequest req, HttpServletRequest request) {
        String jwt = authTokenFilter.parseJwt(request);
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        req.setUsername(username);
        var page = service.findUserGame(req);
        var rs = new PagingResponse<UserGameResponse>()
                .setTotal((int) page.getTotalElements())
                .setData(userGameMapper.userGameToUserGameResponse(page.getContent()));
        return ResponseEntity.ok(rs);
    }

}
