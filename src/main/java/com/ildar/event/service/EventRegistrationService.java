package com.ildar.event.service;

import com.ildar.event.domain.EventRegistration;
import com.ildar.event.dto.EventRegistrationDTO;
import com.ildar.event.dto.mapper.EventRegistrationMapper;
import com.ildar.event.exception.EventNotFoundException;
import com.ildar.event.exception.UserNotFoundException;
import com.ildar.event.repository.eventregistration.EventRegistrationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventRegistrationService {

    private final EventService eventService;
    private final UserService userService;
    private final EventRegistrationRepository eventRegistrationRepository;
    private final EventRegistrationMapper eventRegistrationMapper;

    @Transactional
    public EventRegistrationDTO registerUserForEvent(EventRegistrationDTO dto) {
        log.info("Received request to register user {} for the event {}.", dto.userId(), dto.eventId());
        if (!userService.existsUser(dto.userId())) {
            throw new UserNotFoundException(dto.userId());
        }
        if (!eventService.existsEvent(dto.eventId())) {
            throw new EventNotFoundException(dto.eventId());
        }

        eventService.incrementCurrentRegistrations(dto.eventId());

        EventRegistration eventRegistration =
                eventRegistrationRepository.save(eventRegistrationMapper.createFromDto(dto));
        return eventRegistrationMapper.createDto(eventRegistration);
    }
}
