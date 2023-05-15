package com.example.suites.service;

import com.example.suites.model.Suite;
import com.example.suites.model.dto.ReservationsDto;
import com.example.suites.model.dto.SuitDTO;
import com.example.suites.repository.SuiteRepository;
import com.reservation.Request;
import com.reservation.ReservationGrpcDto;
import com.reservation.Response;
import com.reservation.SuiteServiceGrpc;
import jakarta.ws.rs.GET;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class SuiteService {

    private final SuiteServiceGrpc.SuiteServiceBlockingStub suiteServiceBlockingStub;

    @Autowired
    public SuiteService(SuiteServiceGrpc.SuiteServiceBlockingStub suiteServiceBlockingStub) {
        this.suiteServiceBlockingStub = suiteServiceBlockingStub;
    }

    

    @Autowired
    private SuiteRepository suiteRepository;


    public List<ReservationsDto> allReservations() throws ParseException {
        Request request = Request.newBuilder().build();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        Response response = suiteServiceBlockingStub.getAllReservations(request);

        List<ReservationsDto> reservationsDtos = new ArrayList<>();
        for(ReservationGrpcDto reservationGrpcDto : response.getReservationsList()){
            ReservationsDto reservationsDto = ReservationsDto.builder()
                    .id(reservationGrpcDto.getId())
                    .suiteId(reservationGrpcDto.getSuiteId())
                    .startDate(formatter.parse(reservationGrpcDto.getStartDate()))
                    .endDate(formatter.parse(reservationGrpcDto.getEndDate()))
                    .number((int) reservationGrpcDto.getNumber())
                    .status(reservationGrpcDto.getStatus())
                    .userId(reservationGrpcDto.getUserId())
                    .hostId(reservationGrpcDto.getHostId())
                    .build();

            reservationsDtos.add(reservationsDto);
        }
        return reservationsDtos;
    }

    public Suite create(Suite suite){
        Suite newSuite = suiteRepository.save(suite);
        return newSuite;
    }

    public List<Suite> findAll(){
        return suiteRepository.findAll();
    }

    public List<Suite> findAllByHostId(String id){
        return suiteRepository.findAllByUserId(id);
    }

    public Suite delete(String id){
        Suite suite = suiteRepository.findOneById(id);
        suiteRepository.delete(suite);
        return suite;
    }

    public Suite findOneById(String id){
        return suiteRepository.findOneById(id);
    }

    public Suite update(Suite updateProfileDTO, String id){
        Suite suite = suiteRepository.findOneById(id);
        suite.update(updateProfileDTO);
        return suiteRepository.save(suite);
    }

    public void deleteAllByHostId(String id){
        suiteRepository.deleteAllByUserId(id);
    }

    public List<Suite> findAllByLocation(SuitDTO suitDTO) throws ParseException {
        List<Suite> suites = suiteRepository.findAllByLocation(suitDTO.getLocation());
        List<Suite> retVal = new ArrayList<>();
        List<Suite> povratna = new ArrayList<>();
        for (Suite s: suites){
            if(s.getMinGuests() <= suitDTO.getGuests() && s.getMaxGuests() >= suitDTO.getGuests() &&
                    s.getStartDate().compareTo(suitDTO.getStartDate()) < 0 && s.getEndDate().compareTo(suitDTO.getEndDate()) > 0){
                retVal.add(s);
            }
        }
        List<ReservationsDto> reservations = allReservations();
        for(Suite s: retVal) {
            for(ReservationsDto r : reservations){
                if(r.getSuiteId() == s.getId()){
                    if(suitDTO.getStartDate().after(r.getStartDate()) && suitDTO.getStartDate().before(r.getEndDate())
                    || suitDTO.getEndDate().after(r.getStartDate()) && suitDTO.getEndDate().before(r.getEndDate())){
                    }
                    else {
                        povratna.add(s);
                    }
                }
            }
        }
        return povratna;
    }
}
