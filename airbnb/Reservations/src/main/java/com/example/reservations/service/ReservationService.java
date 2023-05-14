package com.example.reservations.service;

import com.example.reservations.model.Reservation;
import com.example.reservations.model.dto.UpdateStatusDto;
import com.example.reservations.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
                else retVal.add(not);
            }
        }
        return retVal;
    }

    public List<Reservation> allReservations(String hostId){
        List<Reservation> allReservations = findAllByHostId(hostId);
        List<Reservation> retVal = new ArrayList<>();
        for(Reservation res : allReservations){
            String suiteId = res.getSuiteId();
            List<Reservation> accepted = findAllBySuiteIdAndStatus(suiteId);
            List<Reservation> notAccepted = findAllBySuiteId(suiteId);
            List<Reservation> safe = nekiNaziv(accepted, notAccepted);
            for(Reservation ret : safe){
                if(!retVal.contains(ret)) {
                    retVal.add(ret);
                }
            }
        }

        return retVal;
    }
}
