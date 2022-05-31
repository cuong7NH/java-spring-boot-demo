
package com.example.accessingdatamysql.service;

        import com.example.accessingdatamysql.dto.request.CreateEventRequest;
        import com.example.accessingdatamysql.dto.request.CreateUserRequest;
        import com.example.accessingdatamysql.dto.request.EventRequest;
        import com.example.accessingdatamysql.dto.request.GameRequest;
        import com.example.accessingdatamysql.entity.Event;
        import com.example.accessingdatamysql.entity.Game;
        import com.example.accessingdatamysql.entity.User;
        import com.example.accessingdatamysql.repository.EventRepository;
        import com.example.accessingdatamysql.repository.GameRepository;
        import lombok.RequiredArgsConstructor;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.PageRequest;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

        import javax.transaction.Transactional;
        import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final UserService userService;
    private final GameService gameService;
    public Page<Event> findEvents(EventRequest request) {
        var pageSize = PageRequest.of(request.getPage(), request.getSize());
        return eventRepository.filter(request.getId(), request.getGameId(), request.getStartDate(), request.getEndDate(), pageSize);
    }
    @Transactional
    public Event saveEvent(CreateEventRequest createEventRequest) {
        Event event = new Event();
        User user = userService.findById(createEventRequest.getUserId()).orElseThrow(RuntimeException::new);
        Game game = gameService.findById(createEventRequest.getGameId()).orElseThrow(RuntimeException::new);
        event.setStatus(createEventRequest.getStatus());
        event.setTime(createEventRequest.getTime());
        event.setUser(user);
        event.setGame(game);
        return eventRepository.save(event);
    }
}
