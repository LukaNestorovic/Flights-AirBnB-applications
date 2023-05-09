package com.example.demo.model.dto;

public class AccommodationDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer capacity;

    public AccommodationDTO() {
    }

    public AccommodationDTO(Long id, String name, String description, Double price, Integer capacity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
