package com.example.demo.repository;

import com.example.demo.model.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.time.LocalDate;

public interface FlightRepository extends MongoRepository<Flight, String> {

    Flight findOneById(String id);

    List<Flight> findByFromAndWhereAndDateAndRemainingTicketsGreaterThanEqual(
            String from, String where, LocalDate date, Integer remainingTickets);

}
