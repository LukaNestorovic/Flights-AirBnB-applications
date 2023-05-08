package com.example.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/accommodation-requests")
public class AccommodationRequestController {

    @Autowired
    private AccommodationRequestService requestService;

    // kreiranje novog zahteva za smeštaj
    @PostMapping
    public ResponseEntity<AccommodationRequest> createRequest(@Valid @RequestBody AccommodationRequest request) {
        AccommodationRequest createdRequest = requestService.createRequest(request);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    // čitanje svih zahteva za smeštaj
    @GetMapping
    public ResponseEntity<List<AccommodationRequest>> getAllRequests() {
        List<AccommodationRequest> requests = requestService.getAllRequests();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    // čitanje zahteva za smeštaj po ID-u
    @GetMapping("/{id}")
    public ResponseEntity<AccommodationRequest> getRequestById(@PathVariable(value = "id") Long id) {
        AccommodationRequest request = requestService.getRequestById(id);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    // ažuriranje zahteva za smeštaj po ID-u
    @PutMapping("/{id}")
    public ResponseEntity<AccommodationRequest> updateRequest(@PathVariable(value = "id") Long id,
                                                              @Valid @RequestBody AccommodationRequest requestDetails) {
        AccommodationRequest updatedRequest = requestService.updateRequest(id, requestDetails);
        return new ResponseEntity<>(updatedRequest, HttpStatus.OK);
    }

    // brisanje zahteva za smeštaj po ID-u
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRequest(@PathVariable(value = "id") Long id) {
        requestService.deleteRequest(id);
        return ResponseEntity.ok().build();
    }

}
