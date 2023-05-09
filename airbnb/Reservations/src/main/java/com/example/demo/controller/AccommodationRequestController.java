package com.example.demo.controller;

import com.example.demo.model.AccommodationRequest;
import com.example.demo.service.AccommodationRequestService;
import com.example.demo.service.AccommodationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/accommodation-requests")
public class AccommodationRequestController {

    @Autowired
    private AccommodationRequestService requestService;

    @Autowired
    private AccommodationService accommodationService;

    @PostMapping
    public ResponseEntity<AccommodationRequest> createRequest(@Valid @RequestBody AccommodationRequest request) {
        AccommodationRequest createdRequest = requestService.createRequest(request);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AccommodationRequest>> getAllRequests() {
        List<AccommodationRequest> requests = requestService.getAllRequests();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccommodationRequest> getRequestById(@PathVariable(value = "id") Long id) {
        AccommodationRequest request = requestService.getRequestById(id);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccommodationRequest> updateRequest(@PathVariable(value = "id") Long id,
                                                              @Valid @RequestBody AccommodationRequest requestDetails) {
        AccommodationRequest updatedRequest = requestService.updateRequest(id, requestDetails);
        return new ResponseEntity<>(updatedRequest, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRequest(@PathVariable("id") Long id) {
        try {
            requestService.deleteRequest(id);
            accommodationService.markAccommodationAsAvailable(id);
            return ResponseEntity.ok().build();
        } catch (AccommodationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
