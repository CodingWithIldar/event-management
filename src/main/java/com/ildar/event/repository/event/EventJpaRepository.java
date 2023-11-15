package com.ildar.event.repository.event;

import com.ildar.event.domain.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventJpaRepository extends EventRepository, CrudRepository<Event, String> {
}
