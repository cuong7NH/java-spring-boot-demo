
package com.example.accessingdatamysql.api;
        import com.example.accessingdatamysql.dto.PagingResponse;
        import com.example.accessingdatamysql.dto.request.CreateEventRequest;
        import com.example.accessingdatamysql.dto.request.EventRequest;
        import com.example.accessingdatamysql.dto.response.EventResponse;
        import com.example.accessingdatamysql.dto.response.EventResponse2;
        import com.example.accessingdatamysql.entity.CustomUserDetails;
        import com.example.accessingdatamysql.mapper.EventMapper;
        import com.example.accessingdatamysql.security.jwt.AuthTokenFilter;
        import com.example.accessingdatamysql.security.jwt.JwtUtils;
        import com.example.accessingdatamysql.service.EventService;
        import lombok.AllArgsConstructor;
        import lombok.RequiredArgsConstructor;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.security.core.annotation.AuthenticationPrincipal;
        import org.springframework.web.bind.annotation.*;

        import javax.servlet.http.HttpServletRequest;
        import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EventController {
    private final EventService service;
    private final EventMapper eventMapper;
    private  final AuthTokenFilter authTokenFilter;
    private final JwtUtils jwtUtils;

    @GetMapping("/events")
    public ResponseEntity<PagingResponse<EventResponse2>> getEvents(EventRequest req) {
        var page = service.findEvents(req);
        var rs = new PagingResponse<EventResponse2>()
                .setTotal((int) page.getTotalElements() )
                .setData(page.getContent());
        System.out.println("Event user :"+page.getTotalElements());
        return ResponseEntity.ok(rs);
    }
    @GetMapping("/events/{id}")
    public ResponseEntity<PagingResponse<EventResponse2>> getEvents(EventRequest req, HttpServletRequest request) {
        String jwt = authTokenFilter.parseJwt(request);
        var page = service.findEvents(req);
        var rs = new PagingResponse<EventResponse2>()
                .setTotal((int) page.getTotalElements() )
                .setData(page.getContent());
        return ResponseEntity.ok(rs);
    }
    @PostMapping("/events")
    public ResponseEntity createEvent(@Valid @RequestBody CreateEventRequest req) {
        service.addEvent(req);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}

