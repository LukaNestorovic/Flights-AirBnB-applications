package com.example.demo.repository;

import com.example.demo.model.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface FlightRepository extends MongoRepository<Flight, String> {
    Flight findOneById(String id);
    List<Flight> findAllByTakeoffDateAndLandingDateAndFromAndWhere(Date takeoffDate, Date landingDate, String from, String where);
}
