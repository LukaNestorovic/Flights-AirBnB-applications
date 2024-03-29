package com.example.reservations.service;

import com.example.reservations.model.Reservation;
import com.example.reservations.model.dto.CheckDto;
import com.example.reservations.model.dto.UpdateStatusDto;
import com.example.reservations.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Reservation> findAll(){
        return reservationRepository.findAllByStatus(true);
    }

    public List<Reservation> findAllByHostId(String id){
        List<Reservation> reservations = reservationRepository.findAllByHostIdAndStatus(id, false);
        return reservations;
    }

    public List<Reservation> findAllByHostIdStatus(String id){
        List<Reservation> reservations = reservationRepository.findAllByHostIdAndStatus(id, true);
        return reservations;
    }

    public List<Reservation> findAllAcceptedByUserId(String id){
        List<Reservation> reservations = reservationRepository.findAllByUserIdAndStatus(id, true);
        return reservations;
    }

    public Reservation findOneById(String id){
        Reservation reservation = reservationRepository.findOneById(id);
        return reservation;
    }

    public void deleteAllByUserId(String id){
        reservationRepository.deleteAllByUserId(id);
    }

    public List<Reservation> findAllBySuiteIdAndStatus(String id){
        List<Reservation> reservations = reservationRepository.findAllBySuiteIdAndStatus(id, true);
        return reservations;
    }

    public List<Reservation> findAllBySuiteId(String id){
        List<Reservation> reservations = reservationRepository.findAllBySuiteIdAndStatus(id, false);
        return reservations;
    }

    public List<Reservation> nekiNaziv(List<Reservation> accepted, List<Reservation> notAccepted){
        List<Reservation> retVal = new ArrayList<>();
        for(Reservation reservation : accepted){
            for(Reservation not : notAccepted){
                if(not.getStartDate().after(reservation.getStartDate()) && not.getStartDate().before(reservation.getEndDate())
                    || not.getEndDate().after(reservation.getStartDate()) && not.getEndDate().before(reservation.getEndDate())){
                    reservationRepository.delete(not);
                }
                else {
                    retVal.add(not);
                }
            }
        }
        List<Reservation> povratna = retVal.stream().distinct().collect(Collectors.toList());
        return povratna;
    }

    public List<Reservation> allReservations(String hostId){
        List<Reservation> allReservations = findAllByHostId(hostId);
        List<Reservation> retVal = new ArrayList<>();
        for(Reservation res : allReservations){
            String suiteId = res.getSuiteId();
            List<Reservation> accepted = findAllBySuiteIdAndStatus(suiteId);
            List<Reservation> notAccepted = findAllBySuiteId(suiteId);
            if(accepted.isEmpty()) {
                for(Reservation ret : notAccepted){
                    retVal.add(ret);
                }
            }
            else {
                List<Reservation> safe = nekiNaziv(accepted, notAccepted);
                for (Reservation ret : safe) {
                    retVal.add(ret);
                }
            }
        }
        List<Reservation> povratna = retVal.stream().distinct().collect(Collectors.toList());
        return povratna;
    }

    public Boolean reserve(CheckDto checkDto){
        List<Reservation> reservations = findAllBySuiteIdAndStatus(checkDto.getSuiteId());
        for(Reservation reservation : reservations){
            if(checkDto.getStartDate().after(reservation.getStartDate()) && checkDto.getStartDate().before(reservation.getEndDate())
            || checkDto.getEndDate().after(reservation.getStartDate()) && checkDto.getEndDate().before(reservation.getEndDate())){
                return false;
            }
            else return true;
        }
        return true;
    }
}
