package com.example.reservations.controller;

import com.example.reservations.model.Reservation;
import com.example.reservations.model.dto.CheckDto;
import com.example.reservations.model.dto.UpdateStatusDto;
import com.example.reservations.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> findAll(){
        return new ResponseEntity<>(reservationService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<List<Reservation>> findAllByUserId(@PathVariable("id") String id){
        List<Reservation> reservations = reservationService.findAllByUserId(id);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping(path = "/user/{id}")
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<List<Reservation>> findAllAcceptedByUserId(@PathVariable("id") String id){
        List<Reservation> reservations = reservationService.findAllAcceptedByUserId(id);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping(path = "/host/{id}")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<List<Reservation>> findAllByHostId(@PathVariable("id") String id){
        List<Reservation> reservations = reservationService.allReservations(id);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping(path = "/hoststatus/{id}")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<List<Reservation>> findAllByHostIdStatus(@PathVariable("id") String id){
        List<Reservation> reservations = reservationService.findAllByHostIdStatus(id);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping(path = "/suite/{id}")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<List<Reservation>> findAllBySuiteIdAndStatus(@PathVariable("id") String id){
        return new ResponseEntity<>(reservationService.findAllBySuiteIdAndStatus(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('GUEST') or hasRole('HOST')")
    public ResponseEntity<?> deleteReservation(@PathVariable("id") String id){
        return new ResponseEntity<>(reservationService.delete(id), HttpStatus.OK);
    }
    @DeleteMapping(path = "/user/{id}")
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<?> deleteAllByUserId(@PathVariable("id") String id){
        reservationService.deleteAllByUserId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<Reservation> updateReservation(@RequestBody UpdateStatusDto reservation){
        Reservation ret = reservationService.update(reservation);
        if(ret == null){
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(ret,HttpStatus.ACCEPTED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation){
        Reservation newReservation = reservationService.create(reservation);
        return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
    }

    @PostMapping(path = "/check", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<Boolean> reserve(@RequestBody CheckDto checkDto){
        Boolean status = reservationService.reserve(checkDto);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
