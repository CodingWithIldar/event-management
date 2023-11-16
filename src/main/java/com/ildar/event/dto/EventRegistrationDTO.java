package com.ildar.event.dto;

import com.ildar.event.domain.RegistrationStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import org.hibernate.validator.constraints.UUID;

@Builder
public record EventRegistrationDTO(
        String eventRegistrationId,
        @NotEmpty
        @UUID
        String eventId,
        @NotEmpty
        @UUID
        String userId,
        RegistrationStatus registrationStatus
) {
}
