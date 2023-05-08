package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccommodationRequestRepository extends JpaRepository<AccommodationRequest, Long> {

    Optional<AccommodationRequest> findById(Long id);

    void deleteById(Long id);

}

