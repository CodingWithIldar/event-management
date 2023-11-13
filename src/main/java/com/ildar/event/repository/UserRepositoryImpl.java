package com.ildar.event.repository;

import com.ildar.event.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    //User_ID -> User
    private final Map<String, User> users = new ConcurrentHashMap<>();

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return users.values().stream()
                .filter(user -> Objects.equals(user.username(), username))
                .findAny();
    }

    @Override
    public void save(User user) {
        users.put(user.id(), user);
    }
}
