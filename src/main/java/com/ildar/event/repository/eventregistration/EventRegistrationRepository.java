package com.ildar.event.repository.eventregistration;

import com.ildar.event.domain.EventRegistration;

import java.util.Optional;

public interface EventRegistrationRepository {
    EventRegistration save(EventRegistration eventRegistration);

    void deleteAll();

    Optional<EventRegistration> findById(String eventRegistrationId);

    boolean existsByUser_UserIdAndEvent_EventId(String userId, String eventId);
}
