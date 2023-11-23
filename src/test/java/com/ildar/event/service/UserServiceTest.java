package com.ildar.event.service;

import com.ildar.event.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.ildar.event.TestData.USERS;
import static com.ildar.event.TestData.USER_DTOS;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest extends BaseServiceTest {

    @Test
    void getAllUsers_UsersReturned() {
        when(userRepository.findAll()).thenReturn(USERS);
        List<UserDTO> allUsers = userService.getAllUsers();

        assertEquals(USER_DTOS, allUsers);
        verify(userRepository, times(1)).findAll();
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void getAllUsers_NoUsersReturned() {
        when(userRepository.findAll()).thenReturn(emptyList());
        List<UserDTO> allUsers = userService.getAllUsers();

        assertEquals(emptyList(), allUsers);
        verify(userRepository, times(1)).findAll();
        verifyNoMoreInteractions(userRepository);
    }
}