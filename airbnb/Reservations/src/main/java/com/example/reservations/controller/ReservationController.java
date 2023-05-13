package com.example.reservations.controller;

import com.example.reservations.model.Reservation;
import com.example.reservations.model.dto.UpdateStatusDto;
import com.example.reservations.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<List<Reservation>> findAllByUserId(@PathVariable("id") String id){
        List<Reservation> reservations = reservationService.findAllByUserId(id);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping(path = "/host/{id}")
    public ResponseEntity<List<Reservation>> findAllByHostId(@PathVariable("id") String id){
        List<Reservation> reservations = reservationService.findAllByHostId(id);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable("id") String id){
        return new ResponseEntity<>(reservationService.delete(id), HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> updateReservation(@RequestBody UpdateStatusDto reservation){
        System.out.println("aa");
        Reservation ret = reservationService.update(reservation);
        if(ret == null){
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(ret,HttpStatus.ACCEPTED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation){
        Reservation newReservation = reservationService.create(reservation);
        return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
    }
}
