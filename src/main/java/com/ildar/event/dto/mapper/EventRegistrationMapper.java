package com.ildar.event.dto.mapper;

import com.ildar.event.domain.EventRegistration;
import com.ildar.event.domain.RegistrationStatus;
import com.ildar.event.dto.EventRegistrationDTO;
import com.ildar.event.service.EventService;
import com.ildar.event.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventRegistrationMapper {

    private final UserService userService;
    private final EventService eventService;

    public EventRegistration createFromDto(EventRegistrationDTO dto) {
        return EventRegistration.builder()
                .user(userService.getUser(dto.userId()))
                .event(eventService.getEvent(dto.eventId()))
                .registrationStatus(RegistrationStatus.CREATED)
                .build();
    }

    public EventRegistrationDTO createDto(EventRegistration eventRegistration) {
        return EventRegistrationDTO
                .builder()
                .eventRegistrationId(eventRegistration.getEventRegistrationId())
                .eventId(eventRegistration.getEvent().getEventId())
                .userId(eventRegistration.getUser().getUserId())
                .registrationStatus(eventRegistration.getRegistrationStatus())
                .build();
    }
}
