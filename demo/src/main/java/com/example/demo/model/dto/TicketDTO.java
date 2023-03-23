package com.example.demo.model.dto;

import com.example.demo.model.User;

import java.util.Date;

public class TicketDTO {

    private String where;
    private String from;
    private Date date;
    private String userId;
    private String flightId;
    private Integer numberOfTickets;

    public TicketDTO() {
    }

    public TicketDTO(String where, String from, Date date,String userId, String flightId, Integer numberOfTickets) {
        this.where = where;
        this.from = from;
        this.date = date;
        this.userId = userId;
        this.flightId = flightId;
        this.numberOfTickets = numberOfTickets;
    }


    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public Integer getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(Integer numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
}
