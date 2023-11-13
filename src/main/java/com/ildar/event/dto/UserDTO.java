package com.ildar.event.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record UserDTO(
        String userId,
        @NotEmpty
        String username,
        @NotEmpty
        @Email
        String email
) {
}
