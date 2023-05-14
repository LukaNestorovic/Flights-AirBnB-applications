package com.example.profile.repository;

import com.example.profile.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    User findOneById(String id);
    Optional<User> findByUsername(String username);

    User findOneByUsername(String username);

    User findOneByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
