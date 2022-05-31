
package com.example.accessingdatamysql.api;

        import com.example.accessingdatamysql.dto.PagingResponse;
        import com.example.accessingdatamysql.dto.request.CreateEventRequest;
        import com.example.accessingdatamysql.dto.request.CreateUserRequest;
        import com.example.accessingdatamysql.dto.request.EventRequest;
        import com.example.accessingdatamysql.dto.response.EventResponse;
        import com.example.accessingdatamysql.dto.response.UserResponse;
        import com.example.accessingdatamysql.entity.Event;
        import com.example.accessingdatamysql.mapper.EventMapper;
        import com.example.accessingdatamysql.service.EventService;
          import lombok.RequiredArgsConstructor;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EventController {
    private final EventService service;
    private final EventMapper eventMapper;

    @GetMapping("/events")
    public ResponseEntity<PagingResponse<EventResponse>> getGames(EventRequest req) {
        var page = service.findEvents(req);
        var rs = new PagingResponse<EventResponse>()
                .setTotal((int) page.getTotalElements() )
                .setData(eventMapper.eventToEventResponse(page.getContent()));
        return ResponseEntity.ok(rs);
    }

    @PostMapping("/events")
    public ResponseEntity<EventResponse> createEvent(@Valid @RequestBody CreateEventRequest req) {
        var event = service.saveEvent(req);
        return ResponseEntity.ok(eventMapper.eventToEventResponse(event));
    }
}

