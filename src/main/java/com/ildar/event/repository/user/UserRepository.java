package com.ildar.event.repository.user;

import com.ildar.event.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findById(String id);

    Optional<User> findByUsername(String username);

    User save(User user);
}
