package com.ildar.event;

import com.ildar.event.domain.User;
import com.ildar.event.dto.UserDTO;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;

@UtilityClass
public class TestData {

    public static final List<User> USERS = Arrays.asList(
            User.builder()
                    .email("email1@gmail.com")
                    .username("username1")
                    .build(),
            User.builder()
                    .email("email2@gmail.com")
                    .username("username2")
                    .build()
    );

    public static final String EVENT_JSON =
            "{" +
                    "    \"title\": \"Java Conference\",\n" +
                    "    \"description\": \"Java conf for beginners\",\n" +
                    "    \"dateTime\": \"2023-11-16 12:00\",\n" +
                    "    \"location\": \"London\",\n" +
                    "    \"capacity\": \"200\"" +
                    "}";

    public static final List<UserDTO> USER_DTOS = List.of(
            UserDTO.builder()
                    .email("email1@gmail.com")
                    .username("username1")
                    .build(),
            UserDTO.builder()
                    .email("email2@gmail.com")
                    .username("username2")
                    .build()
    );
}
