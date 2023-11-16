package com.ildar.event.exception;

public class EventCapacityExceededException extends RuntimeException {

    public EventCapacityExceededException(String eventId) {
        super("Couldn't register user for the event, the capacity for the event "
                + eventId + " is already at maximum.");
    }
}
