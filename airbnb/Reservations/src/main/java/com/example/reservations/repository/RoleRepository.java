package com.example.reservations.repository;

import com.example.reservations.model.ERole;
import com.example.reservations.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
