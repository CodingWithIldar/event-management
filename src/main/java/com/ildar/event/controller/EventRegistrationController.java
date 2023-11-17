package com.ildar.event.controller;

import com.ildar.event.dto.EventRegistrationDTO;
import com.ildar.event.service.EventRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eventRegistrations")
@RequiredArgsConstructor
public class EventRegistrationController {

    private final EventRegistrationService eventRegistrationService;

    @PostMapping
    public ResponseEntity<EventRegistrationDTO> createEventRegistration(
            @RequestBody @Valid EventRegistrationDTO eventRegistrationDTO) {
        EventRegistrationDTO eventRegistration = eventRegistrationService.registerUserForEvent(eventRegistrationDTO);
        return new ResponseEntity<>(eventRegistration, HttpStatus.CREATED);
    }

    @GetMapping("/{eventRegistrationId}")
    public ResponseEntity<EventRegistrationDTO> getEventRegistration(@PathVariable String eventRegistrationId) {
        return ResponseEntity.ok(eventRegistrationService.getEventRegistrationDTO(eventRegistrationId));
    }
}
