package com.example.eventuser.service;
import com.example.eventuser.dto.request.CreateEventRequest;
import com.example.eventuser.dto.request.EventRequest;
import com.example.eventuser.dto.response.EventResponse2;
import com.example.eventuser.entity.Event;
import com.example.eventuser.entity.UserGame;
import com.example.eventuser.exception.NotFoundRecordException;
import com.example.eventuser.repository.EventRepository;
import com.example.kafka.configuration.TimeEvent;
import com.example.kafka.services.PushEventServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventService {
    private final EventRepository eventRepository;
    private final UserGameService userGameService;

    private final PushEventServices pushEventServices;
    public Page<EventResponse2> findEvents(EventRequest request) {
        var pageSize = PageRequest.of(request.getPage(), request.getSize());

        return eventRepository.filter(
                request.getUsername(),
                pageSize);
    }
    public Optional<Event> findById(Long id){
        return eventRepository.findById(id);
    }
    public Event addEvent(CreateEventRequest req){
        Optional<UserGame> userGame = userGameService.findById(req.getUserGameId());
        if(userGame.isEmpty())throw new NotFoundRecordException("NotFound UserGame with id :  "+ req.getUserGameId());
        Event event = Event.builder()
                .status(req.getStatus())
                .userGame(userGame.get())
                .time(req.getTime())
                .build();
        Event eventAdded = eventRepository.save(event);
        TimeEvent timeEvent = new TimeEvent();
        timeEvent.setStatus(req.getStatus());
        timeEvent.setRealTime(req.getTime());
        timeEvent.setEventId(eventAdded.getId());
        timeEvent.setUserGameId(userGame.get().getId());
        timeEvent.setCurrentTimePlay(userGame.get().getPlayTime());
        try {
            pushEventServices.sendMessage("time-topic", timeEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventAdded;
    }
}
