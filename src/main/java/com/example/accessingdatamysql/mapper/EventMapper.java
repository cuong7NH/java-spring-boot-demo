package com.example.accessingdatamysql.mapper;

import com.example.accessingdatamysql.dto.response.EventResponse;
import com.example.accessingdatamysql.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface EventMapper {
    EventResponse eventToEventResponse(Event event);
    List<EventResponse> eventToEventResponse(List<Event> event);

}
