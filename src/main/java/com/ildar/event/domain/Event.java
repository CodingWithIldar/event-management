package com.ildar.event.domain;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Event(
        String id,
        String title,
        String description,
        LocalDateTime dateTime,
        String location,
        int capacity
) {
}
