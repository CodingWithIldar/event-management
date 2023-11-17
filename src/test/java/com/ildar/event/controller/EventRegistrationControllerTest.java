package com.ildar.event.controller;

import com.ildar.event.dto.EventRegistrationDTO;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static com.ildar.event.TestData.EVENT_JSON;
import static com.ildar.event.TestData.USER_DTOS;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EventRegistrationControllerTest extends BaseControllerTest {

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
        MvcResult mvcResult = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(USER_DTOS.get(0))))
                .andExpect(status().isCreated())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        String createdUserId = JsonPath.parse(json).read("$.userId", String.class);

        mvcResult = mockMvc.perform(post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(EVENT_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        json = mvcResult.getResponse().getContentAsString();
        String createdEventId = JsonPath.parse(json).read("$.eventId", String.class);

        EventRegistrationDTO eventRegistrationDTO = EventRegistrationDTO.builder()
                .eventId(createdEventId)
                .userId(createdUserId)
                .build();

        mockMvc.perform(post("/eventRegistrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventRegistrationDTO)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/eventRegistrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventRegistrationDTO)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.errorMessage",
                        is("Event registration already exists for user "
                                + createdUserId + " and event " + createdEventId)));
    }

    @Test
    public void testCreateEvent_EventCapacityAtMaximum_ShouldThrowEventCapacityExceededException() throws Exception {
        //todo
    }

    @Test
    public void testCreateEvent_SuccessfulCase_ShouldReturn201Created() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(USER_DTOS.get(0))))
                .andExpect(status().isCreated())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        String createdUserId = JsonPath.parse(json).read("$.userId", String.class);

        mvcResult = mockMvc.perform(post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(EVENT_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        json = mvcResult.getResponse().getContentAsString();
        String createdEventId = JsonPath.parse(json).read("$.eventId", String.class);

        EventRegistrationDTO eventRegistrationDTO = EventRegistrationDTO.builder()
                .eventId(createdEventId)
                .userId(createdUserId)
                .build();

        mockMvc.perform(post("/eventRegistrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventRegistrationDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.eventRegistrationId", notNullValue()))
                .andExpect(jsonPath("$.eventId", is(createdEventId)))
                .andExpect(jsonPath("$.userId", is(createdUserId)))
                .andExpect(jsonPath("$.registrationStatus", is("CREATED")));
    }
}
