package com.example.grooming_booking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "services")
public class GroomingService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Integer durationMinutes;

    @Column(name = "price_min")
    private Double priceMin;

    @Column(name = "price_max")
    private Double priceMax;

    public GroomingService() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public Double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}