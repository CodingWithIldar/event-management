package com.ildar.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EventDTO (
    String eventId,
    @NotEmpty
    String title,
    @NotEmpty
    String description,
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime dateTime,
    @NotEmpty
    String location,
    int capacity,
    int currentRegistrations
) {
    
}
