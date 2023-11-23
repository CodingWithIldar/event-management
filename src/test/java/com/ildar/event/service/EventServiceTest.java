package com.ildar.event.service;

import com.ildar.event.dto.EventDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.ildar.event.TestData.EVENTS;
import static com.ildar.event.TestData.EVENT_DTOS;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EventServiceTest extends BaseServiceTest {

    @Test
    void getAllEvent_EventsReturned() {
        when(eventRepository.findAll()).thenReturn(EVENTS);
        List<EventDTO> allEvents = eventService.getAllEvents();

        assertEquals(EVENT_DTOS, allEvents);
        verify(eventRepository, times(1)).findAll();
        verifyNoMoreInteractions(eventRepository);
    }

    @Test
    void getAllEvents_NoEventsReturned() {
        when(eventRepository.findAll()).thenReturn(emptyList());
        List<EventDTO> allEvents = eventService.getAllEvents();

        assertEquals(emptyList(), allEvents);
        verify(eventRepository, times(1)).findAll();
        verifyNoMoreInteractions(eventRepository);
    }
}
