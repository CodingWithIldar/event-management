package com.ildar.event.repository.event;

import com.ildar.event.domain.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepository {

    List<Event> findAll();

    Optional<Event> findById(String id);

    List<Event> findByLocation(String location);

    Event save(Event event);

    boolean existsById(String eventId);

    Optional<Event> selectForUpdate(String eventId);
}
