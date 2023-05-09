package com.example.demo.controller;

import com.example.demo.model.Accommodation;
import com.example.demo.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accommodations")
public class AccommodationController {

    @Autowired
    private AccommodationService accommodationService;

    @GetMapping("/")
    public ResponseEntity<List<Accommodation>> getAllAccommodations() {
        List<Accommodation> accommodations = accommodationService.getAllAccommodations();
        return new ResponseEntity<>(accommodations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> getAccommodationById(@PathVariable Long id) {
        Accommodation accommodation = accommodationService.getAccommodationById(id);
        return new ResponseEntity<>(accommodation, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Accommodation> createAccommodation(@Valid @RequestBody Accommodation accommodation) {
        Accommodation createdAccommodation = accommodationService.createAccommodation(accommodation);
        return new ResponseEntity<>(createdAccommodation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Accommodation> updateAccommodation(@PathVariable Long id, @Valid @RequestBody Accommodation accommodation) {
        Accommodation updatedAccommodation = accommodationService.updateAccommodation(id, accommodation);
        return new ResponseEntity<>(updatedAccommodation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long id) {
        accommodationService.deleteAccommodation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
