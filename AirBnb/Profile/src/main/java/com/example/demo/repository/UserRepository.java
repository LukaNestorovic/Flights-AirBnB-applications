package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    User findOneById(String id);
    Optional<User> findByUsername(String username);

    User findOneByUsername(String username);

    User findOneByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
