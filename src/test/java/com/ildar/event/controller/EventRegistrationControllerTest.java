package com.ildar.event.controller;

import com.ildar.event.domain.Event;
import com.ildar.event.dto.EventRegistrationDTO;
import com.ildar.event.repository.event.EventRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static com.ildar.event.TestData.EVENT_JSON;
import static com.ildar.event.TestData.USER_DTOS;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EventRegistrationControllerTest extends BaseControllerTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void testCreateEventRegistration_UserNotFound_ShouldThrowUserIdNotFoundException() throws Exception {
        //todo
    }

    @Test
    public void testCreateEvent_Registration_EventNotFound_ShouldThrowEventNotFoundException() throws Exception {
        //todo
    }

    @Test
    public void testCreateEvent_EventRegistrationAlreadyExists_ShouldThrowEventRegistrationExistsException()
            throws Exception {
        MvcResult mvcResult = createUser();

        String json = mvcResult.getResponse().getContentAsString();
        String createdUserId = JsonPath.parse(json).read("$.userId", String.class);

        mvcResult = createEvent();

        json = mvcResult.getResponse().getContentAsString();
        String createdEventId = JsonPath.parse(json).read("$.eventId", String.class);

        EventRegistrationDTO eventRegistrationDTO = EventRegistrationDTO.builder()
                .eventId(createdEventId)
                .userId(createdUserId)
                .build();

        registerForEvent(eventRegistrationDTO)
                .andExpect(status().isCreated());

        registerForEvent(eventRegistrationDTO)
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.errorMessage",
                        is("Event registration already exists for user "
                                + createdUserId + " and event " + createdEventId)));
    }

    @Test
    public void testCreateEvent_EventCapacityAtMaximum_ShouldThrowEventCapacityExceededException() throws Exception {
        MvcResult mvcResult = createUser();

        String json = mvcResult.getResponse().getContentAsString();
        String createdUserId = JsonPath.parse(json).read("$.userId", String.class);

        mvcResult = createEvent();

        json = mvcResult.getResponse().getContentAsString();
        String createdEventId = JsonPath.parse(json).read("$.eventId", String.class);

        Event event = eventRepository.findById(createdEventId).orElseThrow();
        event.setCurrentRegistrations(event.getCapacity());
        eventRepository.save(event);

        EventRegistrationDTO eventRegistrationDTO = EventRegistrationDTO.builder()
                .eventId(createdEventId)
                .userId(createdUserId)
                .build();

        registerForEvent(eventRegistrationDTO)
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.errorMessage",
                        is("Couldn't register user for the event, the capacity for the event "
                                + createdEventId + " is already at maximum.")));
    }

    @Test
    public void testCreateEvent_SuccessfulCase_ShouldReturn201Created() throws Exception {
        MvcResult mvcResult = createUser();

        String json = mvcResult.getResponse().getContentAsString();
        String createdUserId = JsonPath.parse(json).read("$.userId", String.class);

        mvcResult = createEvent();

        json = mvcResult.getResponse().getContentAsString();
        String createdEventId = JsonPath.parse(json).read("$.eventId", String.class);

        EventRegistrationDTO eventRegistrationDTO = EventRegistrationDTO.builder()
                .eventId(createdEventId)
                .userId(createdUserId)
                .build();

        registerForEvent(eventRegistrationDTO)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.eventRegistrationId", notNullValue()))
                .andExpect(jsonPath("$.eventId", is(createdEventId)))
                .andExpect(jsonPath("$.userId", is(createdUserId)))
                .andExpect(jsonPath("$.registrationStatus", is("CREATED")));
    }

    private MvcResult createUser() throws Exception {
        return mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(USER_DTOS.get(0))))
                .andExpect(status().isCreated())
                .andReturn();
    }

    private MvcResult createEvent() throws Exception {
        return mockMvc.perform(post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(EVENT_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }

    private ResultActions registerForEvent(EventRegistrationDTO eventRegistrationDTO) throws Exception {
        return mockMvc.perform(post("/eventRegistrations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(eventRegistrationDTO)));
    }
}
