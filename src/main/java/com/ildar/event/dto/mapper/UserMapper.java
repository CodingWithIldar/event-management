package com.ildar.event.dto.mapper;

import com.ildar.event.domain.User;
import com.ildar.event.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    //User - database model
    //UserDTO - object to return to UI

    public User createFromDto(UserDTO userDTO) {
        return User.builder()
                .username(userDTO.username())
                .email(userDTO.email())
//                .eventRegistrations(new ArrayList<>())
                .build();
    }

    public UserDTO createDto(User user) {
        return UserDTO.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
