package com.example.grooming_booking.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "dogs")
public class Dog {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String name;
    private String breed;
    private String size;
    private String coatType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Dog() {}

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getSize() {
        return size;
    }

    public String getCoatType() {
        return coatType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setCoatType(String coatType) {
        this.coatType = coatType;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}