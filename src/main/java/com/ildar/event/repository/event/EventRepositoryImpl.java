package com.ildar.event.repository.event;

import com.ildar.event.domain.Event;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
@Profile("!dev")
public class EventRepositoryImpl implements EventRepository {

    private final Map<String, Event> events = new ConcurrentHashMap<>();

    @Override
    public List<Event> findAll() {
        return new ArrayList<>(events.values());
    }

    @Override
    public Optional<Event> findById(String id) {
        return Optional.ofNullable(events.get(id));
    }

    @Override
    public List<Event> findByLocation(String location) {
        return events.values().stream()
                .filter(event -> event.getLocation().equals(location))
                .collect(Collectors.toList());
    }

    @Override
    public Event save(Event event) {
        event.setEventId(UUID.randomUUID().toString());
        events.put(event.getEventId(), event);
        return event;
    }
}
