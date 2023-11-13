package com.ildar.event.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ildar.event.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.ildar.event.TestData.USER_DTOS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testCreate2UserThenGetThem() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        for (UserDTO userDto : USER_DTOS) {
            mockMvc.perform(post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(userDto)))
                    .andExpect(status().isOk());
        }

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").isString())
                .andExpect(jsonPath("$[0].username").value(USER_DTOS.get(0).username()))
                .andExpect(jsonPath("$[0].email").value(USER_DTOS.get(0).email()))
                .andExpect(jsonPath("$[1].userId").isString())
                .andExpect(jsonPath("$[1].username").value(USER_DTOS.get(1).username()))
                .andExpect(jsonPath("$[1].email").value(USER_DTOS.get(1).email()));
    }
}