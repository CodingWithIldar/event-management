package com.ildar.event.controller;

import com.ildar.event.dto.UserDTO;
import com.ildar.event.repository.eventregistration.EventRegistrationRepository;
import com.ildar.event.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static com.ildar.event.TestData.USER_DTOS;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends BaseControllerTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;

    @BeforeEach
    void beforeTest() {
        eventRegistrationRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testCreate2UserThenGetThem() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        for (UserDTO userDto : USER_DTOS) {
            mockMvc.perform(post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(userDto)))
                    .andExpect(status().isCreated());
        }

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].userId", containsInAnyOrder(notNullValue(), notNullValue())))
                .andExpect(jsonPath("$[*].username",
                        containsInAnyOrder(USER_DTOS.get(0).username(), USER_DTOS.get(1).username())))
                .andExpect(jsonPath("$[*].email",
                        containsInAnyOrder(USER_DTOS.get(0).email(), USER_DTOS.get(1).email())));
    }
}