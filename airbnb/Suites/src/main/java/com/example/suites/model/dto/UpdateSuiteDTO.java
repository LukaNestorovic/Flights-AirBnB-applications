package com.example.suites.model.dto;

import java.util.Date;

public class UpdateSuiteDTO {

    private Integer normalPrice;
    private String selected;
    private Date startDate;
    private Date endDate;

    public UpdateSuiteDTO(Integer normalPrice, String selected, Date startDate, Date endDate) {
        this.normalPrice = normalPrice;
        this.selected = selected;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(Integer normalPrice) {
        this.normalPrice = normalPrice;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
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
