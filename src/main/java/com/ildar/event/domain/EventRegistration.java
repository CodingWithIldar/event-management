package com.ildar.event.domain;

import lombok.Builder;

@Builder
public record EventRegistration(
        String eventId,
        RegistrationStatus registrationStatus
) {
}
