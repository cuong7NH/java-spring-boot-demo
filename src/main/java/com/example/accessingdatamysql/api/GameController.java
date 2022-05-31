package com.example.accessingdatamysql.api;
import com.example.accessingdatamysql.dto.PagingResponse;
import com.example.accessingdatamysql.dto.request.GameRequest;
import com.example.accessingdatamysql.dto.response.GameResponse;
import com.example.accessingdatamysql.mapper.GameMapper;
import com.example.accessingdatamysql.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class GameController {
    private final GameService service;

    private final GameMapper gameMapper;
    @GetMapping("/games")
    public ResponseEntity<PagingResponse<GameResponse>> getGames(GameRequest req) {
        var page = service.findGames(req);
        var rs = new PagingResponse<GameResponse>()
                .setTotal((int) page.getTotalElements())
                .setData(gameMapper.gameToGameResponse(page.getContent()));
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<GameResponse> getGModel(@PathVariable("id") Long id) {
        var rs = service.findById(id).orElseThrow(RuntimeException::new);
        return ResponseEntity.ok(gameMapper.gameToGameResponse(rs));
    }
}
