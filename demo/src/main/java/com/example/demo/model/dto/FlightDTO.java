package com.example.demo.model.dto;

import java.util.Date;

public class FlightDTO {
    private String id;
    private String where;
    private String from;
    private Date date;
    private Double price;

    private Integer remainingTickets;

    public FlightDTO() {
    }

    public FlightDTO(String id, String where, String from, Date date, Double price, Integer remainingTickets) {
        this.id = id;
        this.where = where;
        this.from = from;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
}
