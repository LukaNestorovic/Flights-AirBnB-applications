package com.example.demo.service;

import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.model.dto.TicketDTO;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;

    public Ticket create(TicketDTO ticketDTO){
        System.out.println(ticketDTO.getUserId());
        User user = userRepository.findOneById(ticketDTO.getUserId());
        Ticket newTicket = ticketRepository.save(new Ticket(ticketDTO.getWhere(), ticketDTO.getFrom(), ticketDTO.getDate(), ticketDTO.getSeat(), user));
        return newTicket;
    }
}
