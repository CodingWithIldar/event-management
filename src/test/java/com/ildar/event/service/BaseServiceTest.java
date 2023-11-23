package com.ildar.event.service;

import com.ildar.event.repository.event.EventRepository;
import com.ildar.event.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public abstract class BaseServiceTest {

    @MockBean(name = "userRepository")
    protected UserRepository userRepository;
    @MockBean(name = "eventRepository")
    protected EventRepository eventRepository;
    @Autowired
    protected UserService userService;
    @Autowired
    protected EventService eventService;
}
