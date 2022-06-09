package com.example.accessingdatamysql.api;
import com.example.accessingdatamysql.dto.PagingResponse;
import com.example.accessingdatamysql.dto.request.CreateEventRequest;
import com.example.accessingdatamysql.dto.request.CreateUserGameRequest;
import com.example.accessingdatamysql.dto.request.UserGameRequest;
import com.example.accessingdatamysql.dto.response.MessageResponse;
import com.example.accessingdatamysql.dto.response.UserGameResponse;
import com.example.accessingdatamysql.entity.UserGame;
import com.example.accessingdatamysql.mapper.UserGameMapper;
import com.example.accessingdatamysql.security.jwt.AuthTokenFilter;
import com.example.accessingdatamysql.security.jwt.JwtUtils;
import com.example.accessingdatamysql.service.UserGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserGameController {
    private final UserGameService service;
    private final UserGameMapper userGameMapper;
    private  final AuthTokenFilter authTokenFilter;
    private final JwtUtils jwtUtils;
    @GetMapping("/user-game")
    public ResponseEntity<PagingResponse<UserGameResponse>> getUserGame(UserGameRequest req) {
        var page = service.findUserGame(req);
        var rs = new PagingResponse<UserGameResponse>()
                .setTotal((int) page.getTotalElements())
                .setData(userGameMapper.userGameToUserGameResponse(page.getContent()));
        return ResponseEntity.ok(rs);
    }
    @GetMapping("/user-game/me")
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

    @PostMapping("/user-game")
    public ResponseEntity<MessageResponse> createEvent(HttpServletRequest request, @Valid @RequestBody CreateUserGameRequest req) {
        service.saveUserGame(request, req);
        return ResponseEntity.ok().body(new MessageResponse("Đăng ký game thành công "));
    }




}
