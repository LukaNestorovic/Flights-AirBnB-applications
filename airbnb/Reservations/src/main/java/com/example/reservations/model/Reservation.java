package com.example.reservations.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("reservations")
public class Reservation {
    @Id
    private String id;

    private String suiteId;
    private Date startDate;
    private Date endDate;
    private Integer number;
    private Boolean status;
    private String userId;

    public Reservation() {
    }

    public Reservation(String id, String suiteId, Date startDate, Date endDate, Integer number, Boolean status, String userId) {
        this.id = id;
        this.suiteId = suiteId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.number = number;
        this.status = status;
        this.userId = userId;
    }

    public void update(Reservation update){
        this.status = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSuiteId() {
        return suiteId;
    }

    public void setSuiteId(String suiteId) {
        this.suiteId = suiteId;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
