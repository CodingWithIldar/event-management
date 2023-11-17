package com.ildar.event.dto;

import lombok.Builder;

@Builder
public record ErrorResponseDTO(String errorMessage) {
}
