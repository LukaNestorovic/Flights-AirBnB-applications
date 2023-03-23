package com.example.demo.controller;

import com.example.demo.model.Flight;
import com.example.demo.model.Flight;
import com.example.demo.model.dto.FlightDTO;
import com.example.demo.model.dto.FlightDTO;
import com.example.demo.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<Flight>> findAll(){
        List<Flight> flights = flightService.findAll();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) throws Exception {
        Flight newFlight = flightService.create(flight);
        return new ResponseEntity<>(newFlight, HttpStatus.CREATED);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        return new ResponseEntity<>(flightService.delete(id), HttpStatus.OK);
    }
}
