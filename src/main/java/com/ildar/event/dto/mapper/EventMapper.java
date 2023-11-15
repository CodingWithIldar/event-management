package com.ildar.event.dto.mapper;

import com.ildar.event.domain.Event;
import com.ildar.event.dto.EventDTO;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public EventDTO createDto(Event event) {
        return EventDTO.builder()
                .eventId(event.getEventId())
                .title(event.getTitle())
                .description(event.getDescription())
                .dateTime(event.getDateTime())
                .location(event.getLocation())
                .capacity(event.getCapacity())
                .currentRegistrations(event.getCurrentRegistrations())
                .build();
    }

    public Event createFromDto(EventDTO eventDTO) {
        return Event.builder()
                .eventId(eventDTO.eventId())
                .title(eventDTO.title())
                .description(eventDTO.description())
                .dateTime(eventDTO.dateTime())
                .location(eventDTO.location())
                .capacity(eventDTO.capacity())
                .currentRegistrations(0)
                .build();
    }
}
