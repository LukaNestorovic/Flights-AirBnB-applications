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
}
