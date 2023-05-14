package com.example.suites.service;

import com.example.suites.model.Suite;
import com.example.suites.model.dto.SuitDTO;
import com.example.suites.repository.SuiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuiteService {

    @Autowired
    private SuiteRepository suiteRepository;

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

    public List<Suite> findAllByLocation(SuitDTO suitDTO){
        List<Suite> suites = suiteRepository.findAllByLocation(suitDTO.getLocation());
        System.out.println(suitDTO.getGuests());
        List<Suite> retVal = new ArrayList<Suite>();
        for (Suite s: suites){
            System.out.println(s.getLocation());
            System.out.println(s.getMinGuests());
            System.out.println(s.getStartDate().compareTo(suitDTO.getStartDate()));
            if(s.getMinGuests() <= suitDTO.getGuests() && s.getMaxGuests() >= suitDTO.getGuests() &&
                    s.getStartDate().compareTo(suitDTO.getStartDate()) < 0 && s.getEndDate().compareTo(suitDTO.getEndDate()) > 0){
                retVal.add(s);
            }
        }
        return retVal;
    }
}
