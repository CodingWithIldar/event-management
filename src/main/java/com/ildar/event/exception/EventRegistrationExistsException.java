package com.ildar.event.exception;

public class EventRegistrationExistsException extends RuntimeException {

    public EventRegistrationExistsException(String userId, String eventId) {
        super("Event registration already exists for user " + userId + " and event " + eventId);
    }
}
