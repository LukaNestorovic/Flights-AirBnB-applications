package com.example.demo.model.dto;

import java.time.LocalDateTime;
public class AccommodationRequestDTO {
    private Long id;
    private String status;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private int numberOfGuests;
    private GuestDTO guest;
    private AccommodationDTO accommodation;

    public AccommodationRequestDTO() {}

    public AccommodationRequestDTO(Long id, String status, LocalDateTime checkInDate, LocalDateTime checkOutDate, int numberOfGuests, GuestDTO guest, AccommodationDTO accommodation) {
        this.id = id;
        this.status = status;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuests = numberOfGuests;
        this.guest = guest;
        this.accommodation = accommodation;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public GuestDTO getGuest() {
        return guest;
    }

    public void setGuest(GuestDTO guest) {
        this.guest = guest;
    }

    public AccommodationDTO getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(AccommodationDTO accommodation) {
        this.accommodation = accommodation;
    }
}
