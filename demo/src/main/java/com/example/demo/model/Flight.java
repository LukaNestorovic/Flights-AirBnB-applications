package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("flights")
public class Flight {

    @Id
    private String id;

    private String where;
    private String from;
    private Date date;
    private Double price;
    private Integer remainingTickets;



    public Flight() {
    }

    public Flight(String where, String from, Date date, Double price, Integer remainingTickets) {
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

    public void update(Integer number){
        remainingTickets = remainingTickets - number;
    }
}
