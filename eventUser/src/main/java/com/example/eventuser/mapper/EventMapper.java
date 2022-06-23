package com.example.eventuser.mapper;

import com.example.eventuser.dto.response.EventResponse;
import com.example.eventuser.entity.Event;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface EventMapper {
    EventResponse eventToEventResponse(Event event);
    List<EventResponse> eventToEventResponse(List<Event> event);

}
