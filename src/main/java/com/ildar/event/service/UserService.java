package com.ildar.event.service;

import com.ildar.event.domain.User;
import com.ildar.event.dto.UserDTO;
import com.ildar.event.dto.mapper.UserMapper;
import com.ildar.event.exception.EmailExistsException;
import com.ildar.event.exception.UserNotFoundException;
import com.ildar.event.exception.UsernameExistsException;
import com.ildar.event.repository.user.UserRepository;
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
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UsernameExistsException(user.getUsername());
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailExistsException(user.getEmail());
        }
        userRepository.save(user);
        return userMapper.createDto(user);
    }

    public boolean existsUser(String userId) {
        return userRepository.existsById(userId);
    }

    /**
     * @throws UserNotFoundException When a user with the specified ID wasn't found
     */
    public User getUser(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    /**
     * @throws UserNotFoundException When a user with the specified ID wasn't found
     */
    public UserDTO getUserDTO(String userId) {
        return userMapper.createDto(getUser(userId));
    }
}
