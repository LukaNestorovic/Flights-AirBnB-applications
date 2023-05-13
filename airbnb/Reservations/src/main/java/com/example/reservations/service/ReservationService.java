package com.example.reservations.service;

import com.example.reservations.model.Reservation;
import com.example.reservations.model.dto.UpdateStatusDto;
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

    public Reservation update(UpdateStatusDto updateStatusDto){
        Reservation reservation = reservationRepository.findOneById(updateStatusDto.getId());
        reservation.update(updateStatusDto);
        return reservationRepository.save(reservation);
    }

    public List<Reservation> findAllByUserId(String id){
        List<Reservation> reservations = reservationRepository.findAllByUserId(id);
        return reservations;
    }

    public List<Reservation> findAllByHostId(String id){
        List<Reservation> reservations = reservationRepository.findAllByHostIdAndStatus(id, false);
        return reservations;
    }

    public Reservation findOneById(String id){
        Reservation reservation = reservationRepository.findOneById(id);
        return reservation;
    }
}
