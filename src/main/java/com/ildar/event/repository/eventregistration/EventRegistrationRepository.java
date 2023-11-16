package com.ildar.event.repository.eventregistration;

import com.ildar.event.domain.EventRegistration;

public interface EventRegistrationRepository {
    EventRegistration save(EventRegistration eventRegistration);
}
