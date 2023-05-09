package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.requireNonNull;
@Service
@Transactional
public class AccommodationRequestService {

    @Autowired
    private AccommodationRequestRepository requestRepository;

    public AccommodationRequest createRequest(AccommodationRequest request) {
        if (checkDateOverlap(request)) {
            throw new DateOverlapException("Accommodation request dates overlap with an existing request.");
        }
        return requestRepository.save(request);
    }


    public List<AccommodationRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    public AccommodationRequest getRequestById(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation request", "id", id));
    }

    public AccommodationRequest updateRequest(Long id, AccommodationRequest request) {
        AccommodationRequest existingRequest = requestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation request", "id", id));

        existingRequest.setAccommodation(request.getAccommodation());
        existingRequest.setStartDate(request.getStartDate());
        existingRequest.setEndDate(request.getEndDate());
        existingRequest.setNumberOfGuests(request.getNumberOfGuests());
        existingRequest.setStatus(request.getStatus());

        return requestRepository.save(existingRequest);
    }

    public void deleteRequest(Long id) {
        AccommodationRequest request = requestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation request", "id", id));

        requestRepository.delete(request);
    }

    public List<AccommodationRequest> getRequestsByAccommodation(Accommodation accommodation) {
        return requestRepository.findByAccommodation(accommodation);
    }

    public List<AccommodationRequest> getRequestsByDateRange(LocalDate startDate, LocalDate endDate) {
        return requestRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(startDate, endDate);
    }

    public void validateRequest(AccommodationRequest request) {
        List<AccommodationRequest> conflictingRequests = requestRepository
                .findByAccommodationAndDateRange(request.getAccommodation(), request.getStartDate(), request.getEndDate());

        if (!conflictingRequests.isEmpty()) {
            throw new RequestConflictException("Accommodation request", "There is already a conflicting request for the given accommodation and date range.");
        }
    }

    public void deleteRequest(Long id) {
        AccommodationRequest request = getRequestById(id);

        if (LocalDate.now().isBefore(request.getStartDate())) {
            deleteRequest(id);
        } else {
            throw new RequestCancellationException("Accommodation request", "It's not possible to cancel the request anymore.");
        }
    }

    public boolean checkDateOverlap(AccommodationRequest newRequest) {
        List<AccommodationRequest> existingRequests = requestRepository.findAll();
        for (AccommodationRequest existingRequest : existingRequests) {
            if (existingRequest.getAccommodation().equals(newRequest.getAccommodation())
                    && existingRequest.getEndDate().compareTo(newRequest.getStartDate()) >= 0
                    && existingRequest.getStartDate().compareTo(newRequest.getEndDate()) <= 0) {
                return true;
            }
        }
        return false;
    }



}
