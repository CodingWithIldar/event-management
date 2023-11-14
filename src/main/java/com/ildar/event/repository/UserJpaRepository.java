package com.ildar.event.repository;

import com.ildar.event.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserJpaRepository extends UserRepository, CrudRepository<User, String> {
}
