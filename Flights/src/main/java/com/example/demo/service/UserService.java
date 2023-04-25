package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        List<User> users = userRepository.findAll();
        return users;
    }

}
