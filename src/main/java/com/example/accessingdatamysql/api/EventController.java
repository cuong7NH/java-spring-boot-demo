package com.example.accessingdatamysql.api;
import com.example.accessingdatamysql.dto.PagingResponse;
import com.example.accessingdatamysql.dto.request.CreateEventRequest;
import com.example.accessingdatamysql.dto.request.EventRequest;
import com.example.accessingdatamysql.dto.response.EventResponse2;
import com.example.accessingdatamysql.dto.response.MessageResponse;
import com.example.accessingdatamysql.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService service;
    @GetMapping("")
    public ResponseEntity<PagingResponse<EventResponse2>> getEvents(EventRequest req) {
        var page = service.findEvents(req);
        var rs = new PagingResponse<EventResponse2>()
                .setTotal((int) page.getTotalElements() )
                .setData(page.getContent());
        System.out.println("Event user :"+page.getTotalElements());
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagingResponse<EventResponse2>> getEventsById(EventRequest req) {
        var page = service.findEvents(req);
        var rs = new PagingResponse<EventResponse2>()
                .setTotal((int) page.getTotalElements() )
                .setData(page.getContent());
        return ResponseEntity.ok(rs);
    }
    @PostMapping("")
    public ResponseEntity<MessageResponse> createEvent(@Valid @RequestBody CreateEventRequest req) {
        service.addEvent(req);
        return ResponseEntity.ok().body(new MessageResponse("Push Event " + req.getStatus() + " success!"));
    }
}

