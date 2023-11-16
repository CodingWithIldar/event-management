package com.ildar.event.service;

import com.ildar.event.domain.Event;
import com.ildar.event.dto.EventDTO;
import com.ildar.event.dto.mapper.EventMapper;
import com.ildar.event.exception.EventCapacityExceededException;
import com.ildar.event.exception.EventNotFoundException;
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

    public boolean existsEvent(String eventId) {
        return eventRepository.existsById(eventId);
    }

    /**
     * @throws EventCapacityExceededException Can't increment current registrations count due to capacity being at limit
     */
    public void incrementCurrentRegistrations(String eventId) {
        Event event = eventRepository.selectForUpdate(eventId)
                .orElseThrow(() -> new EventNotFoundException(eventId));
        if (event.getCurrentRegistrations() < event.getCapacity()) {
            event.incrementCurrentRegistrations();
        }
        else {
            throw new EventCapacityExceededException(eventId);
        }
    }

    /**
     * @throws EventNotFoundException When an event with the specified ID wasn't found
     */
    public Event getEvent(String eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException(eventId));
    }
}
