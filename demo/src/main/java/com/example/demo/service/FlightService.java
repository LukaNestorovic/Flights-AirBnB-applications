package com.example.demo.service;

import com.example.demo.model.Flight;
import com.example.demo.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;


    public List<Flight> findAll(){
        List<Flight> flights = this.flightRepository.findAll();
        return flights;
    }

    public Flight create(Flight flight){
        Flight newFlight = this.flightRepository.save(flight);
        return newFlight;
    }

    public Flight delete(String id) {
        Flight flight = flightRepository.findOneById(id);
        flightRepository.delete(flight);
        return flight;
    }

    public Flight findOneById(String id){
        Flight flight = flightRepository.findOneById(id);
        return flight;
    }

    public List<Flight> searchFlights(String from, String where, LocalDate date, Integer passengers) {
        return this.flightRepository.findByFromAndWhereAndDateAndRemainingTicketsGreaterThanEqual(from, where, date, passengers);
    }


}
