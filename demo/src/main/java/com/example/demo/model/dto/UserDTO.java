package com.example.demo.model.dto;

import com.example.demo.model.Ticket;

import java.util.List;

public class UserDTO {
    private String id;

    private String name;
    private String surname;
    private String email;
    private String telephone;
    private List<Ticket> tickets;

    public UserDTO() {
    }

    public UserDTO(String id, String name, String surname, String email, String telephone, List<Ticket> tickets) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.telephone = telephone;
        this.tickets = tickets;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
