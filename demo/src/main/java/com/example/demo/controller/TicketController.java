package com.example.demo.controller;

import com.example.demo.model.Flight;
import com.example.demo.model.Ticket;
import com.example.demo.model.dto.TicketDTO;
import com.example.demo.service.FlightService;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }
    @Autowired
    public FlightService flightService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketDTO ticket) throws Exception {
        Flight flight = flightService.findOneById(ticket.getFlightId());
        if((flight.getRemainingTickets() - ticket.getNumberOfTickets()) < 0){
            System.out.println("aaa");
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            System.out.println("bbb");
            for (int i = 0; i < ticket.getNumberOfTickets() - 1; i++) {
                Ticket newTicket = ticketService.create(ticket);
            }
            Ticket newTicket = ticketService.create(ticket);

            flight.update(ticket.getNumberOfTickets());
            flightService.create(flight);
            return new ResponseEntity<>(newTicket, HttpStatus.CREATED);
        }
    }
}
