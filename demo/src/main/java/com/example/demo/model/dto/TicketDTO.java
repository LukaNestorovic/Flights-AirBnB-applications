package com.example.demo.model.dto;

import com.example.demo.model.User;

import java.util.Date;

public class TicketDTO {

    private String where;
    private String from;
    private Date takeoffDate;
    private Date landingDate;
    private Double price;
    private String userId;
    private String flightId;
    private Integer numberOfTickets;

    public TicketDTO() {
    }

    public TicketDTO(String where, String from, Date takeoffDate, Date landingDate, Double price, String userId, String flightId, Integer numberOfTickets) {
        this.where = where;
        this.from = from;
        this.takeoffDate = takeoffDate;
        this.landingDate = landingDate;
        this.price = price;
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

    public Date getTakeoffDate() {
        return takeoffDate;
    }

    public void setTakeoffDate(Date takeoffDate) {
        this.takeoffDate = takeoffDate;
    }

    public Date getLandingDate() {
        return landingDate;
    }

    public void setLandingDate(Date landingDate) {
        this.landingDate = landingDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
