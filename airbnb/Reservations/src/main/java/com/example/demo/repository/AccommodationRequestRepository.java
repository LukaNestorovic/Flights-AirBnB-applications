package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccommodationRequestRepository extends JpaRepository<AccommodationRequest, Long> {

    // metoda za pronalaženje zahteva po ID-u
    Optional<AccommodationRequest> findById(Long id);

    // metoda za brisanje zahteva po ID-u
    void deleteById(Long id);

}

