package com.example.demo.repository;

import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    User findOneById(String id);
    User findOneByEmail(String email);
}
