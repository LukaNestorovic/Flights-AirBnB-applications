package com.example.reservations.repository;

import com.example.reservations.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
    Reservation findOneById(String id);

    List<Reservation> findAllByUserId(String id);
}
