package com.example.suites.service;

import com.example.suites.model.Suite;
import com.example.suites.model.dto.ReservationDto;
import com.example.suites.model.dto.SuitDTO;
import com.example.suites.repository.SuiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuiteService {

    @Autowired
    private SuiteRepository suiteRepository;

    private final WebClient webClient;

    public SuiteService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<ReservationDto> allReservations() {
        ReservationDto[] list = webClient.get()
                .uri("http://localhost:8085/api/reservations")
                .retrieve()
                .bodyToMono(ReservationDto[].class)
                .block();
        List<ReservationDto> reservationsDtos = Arrays.asList(list);
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

    public List<Suite> findAllByLocation(SuitDTO suitDTO) {
        List<Suite> suites = suiteRepository.findAllByLocation(suitDTO.getLocation());
        List<Suite> retVal = new ArrayList<>();
        List<Suite> povratna = new ArrayList<>();

        for (Suite s: suites){
            if(s.getMinGuests() <= suitDTO.getGuests() && s.getMaxGuests() >= suitDTO.getGuests() &&
                    s.getStartDate().compareTo(suitDTO.getStartDate()) < 0 && s.getEndDate().compareTo(suitDTO.getEndDate()) > 0){
                retVal.add(s);
            }
        }
        List<ReservationDto> reservations = allReservations();
        for(Suite s: retVal) {
            for(ReservationDto r : reservations){
                System.out.println(r.getSuiteId() + " " + s.getId());
                if(r.getSuiteId().equals(s.getId())){
                    if(suitDTO.getStartDate().after(r.getStartDate()) && suitDTO.getStartDate().before(r.getEndDate())
                            || suitDTO.getEndDate().after(r.getStartDate()) && suitDTO.getEndDate().before(r.getEndDate())){
                        System.out.println("ovde");
                        List<Suite> ret = new ArrayList<>();
                        return ret;
                    }
                    else {
                        System.out.println("ovde2");
                        povratna.add(s);
                    }
                }
                else {
                    System.out.println("ovde3");
                    povratna.add(s);
                }
            }
        }
        List<Suite> proba = povratna.stream().distinct().collect(Collectors.toList());
        return proba;
    }
}
