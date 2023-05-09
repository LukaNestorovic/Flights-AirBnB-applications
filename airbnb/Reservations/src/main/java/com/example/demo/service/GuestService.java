package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Guest;
import com.example.demo.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    public Guest createGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    public Guest getGuestById(Long id) {
        return guestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guest", "id", id));
    }

    public Guest updateGuest(Long id, Guest guest) {
        Guest existingGuest = guestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guest", "id", id));

        existingGuest.setFirstName(guest.getFirstName());
        existingGuest.setLastName(guest.getLastName());
        existingGuest.setEmail(guest.getEmail());
        existingGuest.setPhoneNumber(guest.getPhoneNumber());
        existingGuest.setAddress(guest.getAddress());

        return guestRepository.save(existingGuest);
    }

    public void deleteGuest(Long id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guest", "id", id));

        guestRepository.delete(guest);
    }
}
