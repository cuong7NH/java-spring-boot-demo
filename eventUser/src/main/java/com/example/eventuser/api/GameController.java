package com.example.eventuser.api;

import com.example.eventuser.dto.PagingResponse;
import com.example.eventuser.dto.request.CreateGameRequest;
import com.example.eventuser.dto.request.GameRequest;
import com.example.eventuser.dto.response.GameCountEventResponse;
import com.example.eventuser.dto.response.GameResponse;
import com.example.eventuser.dto.response.GameResponse2;
import com.example.eventuser.dto.response.MessageResponse;
import com.example.eventuser.mapper.GameMapper;
import com.example.eventuser.repository.UserRepository;
import com.example.eventuser.security.jwt.AuthTokenFilter;
import com.example.eventuser.security.jwt.JwtUtils;
import com.example.eventuser.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class GameController {
    private final GameService service;
    private final GameMapper gameMapper;
    private  final AuthTokenFilter authTokenFilter;
    private final JwtUtils jwtUtils;

    private final UserRepository userRepository;
    @GetMapping("/games")
    public ResponseEntity<PagingResponse<GameResponse2>> getGames(GameRequest req) {
        var page = service.findGames(req);
        var rs = new PagingResponse<GameResponse2>()
                .setTotal((int) page.getTotalElements())
                .setData(page.getContent());
        return ResponseEntity.ok(rs);
    }
    @GetMapping("/games/count-event")
    ResponseEntity<List<GameCountEventResponse>> getUserListCountEvent(){
        return new ResponseEntity<>(service.getGameListCountEvent(), HttpStatus.OK);
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<GameResponse> getGameById(@PathVariable("id") Long id) {
        var rs = service.findById(id).orElseThrow(RuntimeException::new);
        return ResponseEntity.ok(gameMapper.gameToGameResponse(rs));
    }
    @PostMapping("/games")
    public ResponseEntity<MessageResponse> createEvent(@Valid @RequestBody CreateGameRequest req) {
        service.addGame(req);
        return ResponseEntity.ok().body(new MessageResponse("Create Game Success!"));
    }

//    @GetMapping("/games/me")
//    public ResponseEntity<GameResponse> getGameById(HttpServletRequest request) {
//        String jwt = authTokenFilter.parseJwt(request);
//        String id = jwtUtils.getUserIdFromJwtToken(jwt);
//        System.out.println("id" + id);
//        var rs = service.findById(Long.valueOf(id)).orElseThrow(RuntimeException::new);
//        return ResponseEntity.ok(gameMapper.gameToGameResponse(rs));
//    }
}
