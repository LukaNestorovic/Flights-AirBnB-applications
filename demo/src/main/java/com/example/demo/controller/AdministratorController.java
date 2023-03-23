package com.example.demo.controller;

import com.example.demo.model.Administrator;
import com.example.demo.model.dto.AdministratorDTO;
import com.example.demo.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/administrators")
@CrossOrigin(origins = "*")
public class AdministratorController {
    private final AdministratorService administratorService;

    @Autowired
    public AdministratorController(AdministratorService administratorService){
        this.administratorService = administratorService;
    }

/*    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdministratorDTO> createAdministrator(@RequestBody AdministratorDTO administratorDTO) throws Exception {
        Administrator administrator = new Administrator(administratorDTO.getName(), administratorDTO.getSurname(), administratorDTO.getEmail(), administratorDTO.getTelephone());
        Administrator newAdministrator = administratorService.create(administrator);
        AdministratorDTO newAdministratorDTO = new AdministratorDTO(newAdministrator.getId(), newAdministrator.getName(), newAdministrator.getSurname(), newAdministrator.getEmail(), newAdministrator.getTelephone());

        return new ResponseEntity<>(newAdministratorDTO, HttpStatus.CREATED);
    }*/

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Administrator> createAdministrator(@RequestBody Administrator administrator) throws Exception {
        Administrator newAdministrator = administratorService.create(administrator);

        return new ResponseEntity<>(newAdministrator, HttpStatus.CREATED);
    }
}
