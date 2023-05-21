package com.example.reservations.repository;

import com.example.reservations.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
    Reservation findOneById(String id);
    List<Reservation> findAllByUserId(String id);
    List<Reservation> findAllByHostIdAndStatus(String id, Boolean status);
    List<Reservation> findAllByUserIdAndStatus(String id, Boolean status);
    void deleteAllByUserId(String id);
    List<Reservation> findAllBySuiteIdAndStatus(String id, Boolean status);
    List<Reservation> findAllBySuiteId(String id);
    List<Reservation> findAllByStatus(Boolean status);
}
