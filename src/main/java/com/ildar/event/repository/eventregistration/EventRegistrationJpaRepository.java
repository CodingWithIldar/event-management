package com.ildar.event.repository.eventregistration;

import com.ildar.event.domain.EventRegistration;
import org.springframework.data.repository.CrudRepository;

public interface EventRegistrationJpaRepository
        extends EventRegistrationRepository, CrudRepository<EventRegistration, String> {
}
