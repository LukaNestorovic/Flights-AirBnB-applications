package com.example.suites.model.dto;

import java.util.Date;

public class SuitDTO {

    private String location;
    private Integer guests;
    private Date startDate;
    private Date endDate;

    public SuitDTO(String location, Integer guests, Date startDate, Date endDate) {
        this.location = location;
        this.guests = guests;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getGuests() {
        return guests;
    }

    public void setGuests(Integer guests) {
        this.guests = guests;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
