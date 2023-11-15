package com.ildar.event.service;

import com.ildar.event.domain.Event;
import com.ildar.event.dto.EventDTO;
import com.ildar.event.dto.mapper.EventMapper;
import com.ildar.event.repository.event.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(eventMapper::createDto)
                .collect(Collectors.toList());
    }

    public EventDTO createEvent(EventDTO eventDto) {
        Event event = eventMapper.createFromDto(eventDto);
        eventRepository.save(event);
        return eventMapper.createDto(event);
    }
}
