package com.example.accessingdatamysql.service;
import com.example.accessingdatamysql.dto.request.CreateEventRequest;
import com.example.accessingdatamysql.dto.request.EventRequest;
import com.example.accessingdatamysql.dto.response.EventResponse2;
import com.example.accessingdatamysql.entity.Event;
import com.example.accessingdatamysql.entity.UserGame;
import com.example.accessingdatamysql.exception.NotFoundException;
import com.example.accessingdatamysql.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventService {
    private final EventRepository eventRepository;
    private final UserGameService userGameService;

    public Page<EventResponse2> findEvents(EventRequest request) {
        var pageSize = PageRequest.of(request.getPage(), request.getSize());

        return eventRepository.filter(
                request.getUsername(),
                pageSize);
    }
    Optional<Event> findById(Long id){
        return eventRepository.findById(id);
    }
    public Event addEvent(CreateEventRequest req){
        Optional<UserGame> userGame = userGameService.findById(req.getUserGameId());
        if(!userGame.isPresent()) throw new NotFoundException("NotFound UserGame with id :  "+ req.getUserGameId());
        Event event= new Event();
        event.setUserGame(userGame.get());
        event.setStatus(req.getStatus());
        event.setTime(req.getTime());
        var lastEventId = userGame.get().getLastEventId();
        Optional<Event> x = lastEventId == null ? Optional.empty() :eventRepository.findById(userGame.get().getLastEventId());
        var timeUpdate = userGame.get().getPlayTime();
        Event event2 = eventRepository.save(event);
        if (x.isPresent() && Objects.equals(event2.getStatus(), "PLAYING") && Objects.equals(req.getStatus(), "PLAYING")) {
            long diffInSeconds = ChronoUnit.SECONDS.between(x.get().getTime(), event2.getTime());
            if (diffInSeconds < 60) {
                timeUpdate = userGame.get().getPlayTime() + diffInSeconds;
            }
        }
        userGameService.updateUserGame(event2.getId(), timeUpdate, userGame.get().getId());
        return event2;
    }
}
