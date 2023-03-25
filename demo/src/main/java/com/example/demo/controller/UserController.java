package com.example.demo.controller;

import com.example.demo.model.Administrator;
import com.example.demo.model.User;
import com.example.demo.model.dto.LogInDTO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.service.AdministratorService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final AdministratorService administratorService;
    @Autowired
    public UserController(UserService userService, AdministratorService administratorService) {
        this.userService = userService;
        this.administratorService = administratorService;
    }



    @PostMapping(path = "/users",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody UserDTO user) throws Exception {
        User newUser = userService.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LogInDTO> logIn(@RequestBody LogInDTO logInDTO){
        List<User> users = userService.findAll();
        List<Administrator> administrators = administratorService.findAll();
        LogInDTO logIn = new LogInDTO(logInDTO.getId(), logInDTO.getPassword(), logInDTO.getEmail());

        for(User user : users){
            if(user.getEmail().equals(logIn.getEmail())){
                if(user.getPassword().equals(logIn.getPassword())){
                    logIn.setId(user.getId());
                    return new ResponseEntity<>(logIn, HttpStatus.OK);
                }
                else return new ResponseEntity<>(logIn, HttpStatus.BAD_REQUEST);
            }
        }

        for(Administrator administrator : administrators){
            if(administrator.getEmail().equals(logIn.getEmail())){
                if(administrator.getPassword().equals(logIn.getPassword())){
                    logIn.setId(administrator.getId());
                    return new ResponseEntity<>(logIn, HttpStatus.OK);
                }
                else return new ResponseEntity<>(logIn, HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(logIn, HttpStatus.NOT_FOUND);
    }
}
