package com.ildar.event.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ildar.event.repository.eventregistration.EventRegistrationRepository;
import com.ildar.event.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class BaseControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;

    @BeforeEach
    void beforeTest() {
        eventRegistrationRepository.deleteAll();
        userRepository.deleteAll();
    }
}
