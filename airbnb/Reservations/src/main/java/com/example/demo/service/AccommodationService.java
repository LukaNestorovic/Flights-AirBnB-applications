package com.example.demo.service;
import com.example.demo.exception.AccommodationException;
import com.example.demo.model.Accommodation;
import com.example.demo.repository.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccommodationService {

    @Autowired
    private AccommodationRepository accommodationRepository;

    public void markAccommodationAsUnavailable(Long accommodationId) throws AccommodationException {
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new AccommodationException("Accommodation not found"));

        if (!accommodation.isAvailable()) {
            throw new AccommodationException("Accommodation is already unavailable");
        }

        accommodation.setAvailable(false);
        accommodationRepository.save(accommodation);
    }

    public void markAccommodationAsAvailable(Long accommodationId) throws AccommodationException {
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new AccommodationException("Accommodation not found"));

        if (accommodation.isAvailable()) {
            throw new AccommodationException("Accommodation is already available");
        }

        accommodation.setAvailable(true);
        accommodationRepository.save(accommodation);
    }
}