package com.example.demo.model.dto;

import java.util.Date;

public class SearchDTO {
    private String where;
    private String from;
    private Date takeoffDate;
    private Date landingDate;
    private Integer remainingTickets;

    public SearchDTO() {
    }

    public SearchDTO(String where, String from, Date takeoffDate, Date landingDate, Integer remainingTickets) {
        this.where = where;
        this.from = from;
        this.takeoffDate = takeoffDate;
        this.landingDate = landingDate;
        this.remainingTickets = remainingTickets;
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

    public Integer getRemainingTickets() {
        return remainingTickets;
    }

    public void setRemainingTickets(Integer remainingTickets) {
        this.remainingTickets = remainingTickets;
    }
}
