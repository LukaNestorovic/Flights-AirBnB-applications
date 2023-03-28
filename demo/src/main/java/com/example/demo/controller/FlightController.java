package com.example.demo.controller;

import com.example.demo.model.Flight;
import com.example.demo.model.Flight;
import com.example.demo.model.dto.FlightDTO;
import com.example.demo.model.dto.FlightDTO;
import com.example.demo.model.dto.SearchDTO;
import com.example.demo.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "*")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<Flight>> findAll(){
        List<Flight> flights = flightService.findAll();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) throws Exception {
        System.out.println(flight.getTakeoffDate());
        System.out.println(flight.getLandingDate());
        Flight newFlight = flightService.create(flight);
        return new ResponseEntity<>(newFlight, HttpStatus.CREATED);
    }

    @DeleteMapping(path="/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        return new ResponseEntity<>(flightService.delete(id), HttpStatus.OK);
    }

    @PostMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Flight>> findSearch(@RequestBody SearchDTO searchDTO){
        List<Flight> flights = flightService.findSearch(searchDTO);
        List<Flight> ispravna = new ArrayList<>();
        for(Flight flight: flights){
            if(flight.getRemainingTickets() > searchDTO.getRemainingTickets()){
                ispravna.add(flight);
            }
        }
        return new ResponseEntity<>(ispravna, HttpStatus.OK);
    }
}
