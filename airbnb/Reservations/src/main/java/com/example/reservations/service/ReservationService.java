package com.example.reservations.service;

import com.example.reservations.model.Reservation;
import com.example.reservations.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation create(Reservation reservation){
        Reservation newReservation = reservationRepository.save(reservation);
        return newReservation;
    }

    public Reservation delete(String id){
        Reservation reservation = reservationRepository.findOneById(id);
        reservationRepository.delete(reservation);
        return reservation;
    }

    public Reservation update(String id){
        Reservation reservation = reservationRepository.findOneById(id);
        reservation.update(reservation);
        return reservationRepository.save(reservation);
    }

    public List<Reservation> findAllByUserId(String id){
        List<Reservation> reservations = reservationRepository.findAllByUserId(id);
        return reservations;
    }

    public Reservation findOneById(String id){
        Reservation reservation = reservationRepository.findOneById(id);
        return reservation;
    }
}
