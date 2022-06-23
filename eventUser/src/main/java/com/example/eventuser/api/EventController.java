package com.example.eventuser.api;
import com.example.eventuser.dto.PagingResponse;
import com.example.eventuser.dto.request.CreateEventRequest;
import com.example.eventuser.dto.request.EventRequest;
import com.example.eventuser.dto.response.EventResponse2;
import com.example.eventuser.dto.response.MessageResponse;
import com.example.eventuser.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

