package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("suites")
public class Suite {

    @Id
    private String id;

    private String name;
    private String location;
    private String features;
    private Integer minGuests;
    private Integer maxGuests;
    private Integer normalPrice;
    private String selected;
    private Date startDate;
    private Date endDate;
    private Boolean reserved;

    public Suite() {

    }

    public Suite(String id, String name, String location, String features, Integer minGuests, Integer maxGuests, Integer normalPrice, String selected, Date startDate, Date endDate, Boolean reserved) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.features = features;
        this.minGuests = minGuests;
        this.maxGuests = maxGuests;
        this.normalPrice = normalPrice;
        this.selected = selected;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reserved = reserved;
    }

    public void update(Suite updateProfileDTO){
        if(updateProfileDTO.getNormalPrice() != null) this.normalPrice = updateProfileDTO.getNormalPrice();
        if(updateProfileDTO.getSelected() != null) this.selected = updateProfileDTO.getSelected();
        if(updateProfileDTO.getStartDate() != null) this.startDate = updateProfileDTO.getStartDate();
        if(updateProfileDTO.getEndDate() != null) this.endDate = updateProfileDTO.getEndDate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }


    public Integer getMinGuests() {
        return minGuests;
    }

    public void setMinGuests(Integer minGuests) {
        this.minGuests = minGuests;
    }

    public Integer getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(Integer maxGuests) {
        this.maxGuests = maxGuests;
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

    public Boolean getReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
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
