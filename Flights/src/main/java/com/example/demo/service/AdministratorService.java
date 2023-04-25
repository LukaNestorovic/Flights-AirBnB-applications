package com.example.demo.service;

import com.example.demo.model.Administrator;
import com.example.demo.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdministratorService {
    @Autowired private AdministratorRepository administratorRepository;


   public List<Administrator> findAll(){
        List<Administrator> administrators = this.administratorRepository.findAll();
        return administrators;
    }

    public Administrator create(Administrator administrator){
        Administrator newAdministrator = this.administratorRepository.save(administrator);
        return newAdministrator;
    }
}