package com.ildar.event.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String eventId;

    private String title;

    private String description;

    private LocalDateTime dateTime;

    private String location;

    private int capacity;

    private int currentRegistrations;

    @OneToMany(mappedBy = "event")
    private List<EventRegistration> eventRegistrations;

    public void incrementCurrentRegistrations() {
        currentRegistrations++;
    }
}
