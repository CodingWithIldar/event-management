package com.ildar.event.domain;

import lombok.Builder;

import java.util.List;

@Builder
public record User(
        String id,
        String username,
        String email,
        List<EventRegistration> eventRegistrations
) {
}
