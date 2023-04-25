package com.example.demo.service;

import com.example.demo.model.Flight;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.model.dto.TicketDTO;
import com.example.demo.repository.FlightRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FlightRepository flightRepository;

    public Ticket create(TicketDTO ticketDTO){
        User user = userRepository.findOneById(ticketDTO.getUserId());
        Flight flight = flightRepository.findOneById(ticketDTO.getFlightId());
        Ticket newTicket = ticketRepository.save(new Ticket(ticketDTO.getWhere(), ticketDTO.getFrom(), ticketDTO.getTakeoffDate(), ticketDTO.getLandingDate(), ticketDTO.getPrice(),  user, flight));
        return newTicket;
    }

    public List<Ticket> findAllByUserId(String id){
        List<Ticket> tickets = this.ticketRepository.findAllByUserId(id);
        return tickets;
    }
}
