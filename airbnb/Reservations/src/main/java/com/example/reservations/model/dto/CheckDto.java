package com.example.reservations.model.dto;

import java.util.Date;

public class CheckDto {
    private Date startDate;
    private Date endDate;
    private String suiteId;

    public CheckDto() {
    }

    public CheckDto(Date startDate, Date endDate, String suiteId) {
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getSuiteId() {
        return suiteId;
    }

    public void setSuiteId(String suiteId) {
        this.suiteId = suiteId;
    }
}
