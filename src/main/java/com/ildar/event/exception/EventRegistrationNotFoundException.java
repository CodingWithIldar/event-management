package com.ildar.event.exception;

public class EventRegistrationNotFoundException extends RuntimeException {

    public EventRegistrationNotFoundException(String eventRegistrationId) {
        super("Event registration " + eventRegistrationId + " not found.");
    }
}
