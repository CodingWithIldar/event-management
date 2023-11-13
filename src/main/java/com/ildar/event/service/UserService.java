package com.ildar.event.service;

import com.ildar.event.domain.User;
import com.ildar.event.dto.UserDTO;
import com.ildar.event.dto.mapper.UserMapper;
import com.ildar.event.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::createDto)
                .collect(Collectors.toList());
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.createFromDto(userDTO);
        userRepository.save(user);
        return userMapper.createDto(user);
    }
}
