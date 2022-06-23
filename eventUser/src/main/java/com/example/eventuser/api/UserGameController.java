package com.example.eventuser.api;

import com.example.eventuser.dto.PagingResponse;
import com.example.eventuser.dto.request.CreateUserGameRequest;
import com.example.eventuser.dto.request.UserGameRequest;
import com.example.eventuser.dto.response.MessageResponse;
import com.example.eventuser.dto.response.UserGameResponse;
import com.example.eventuser.mapper.UserGameMapper;
import com.example.eventuser.security.jwt.AuthTokenFilter;
import com.example.eventuser.security.jwt.JwtUtils;
import com.example.eventuser.service.UserGameService;
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
