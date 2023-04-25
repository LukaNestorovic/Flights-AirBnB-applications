package com.example.demo.model.dto;

import java.util.Date;

public class FlightDTO {
    private String id;
    private String where;
    private String from;
    private Date takeoffDate;
    private Date landingDate;
    private Double price;

    private Integer remainingTickets;

    public FlightDTO() {
    }

    public FlightDTO(String id, String where, String from, Date takeoffDate, Date landingDate, Double price, Integer remainingTickets) {
        this.id = id;
        this.where = where;
        this.from = from;
        this.takeoffDate = takeoffDate;
        this.landingDate = landingDate;
        this.price = price;
        this.remainingTickets = remainingTickets;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getRemainingTickets() {
        return remainingTickets;
    }

    public void setRemainingTickets(Integer remainingTickets) {
        this.remainingTickets = remainingTickets;
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
}
