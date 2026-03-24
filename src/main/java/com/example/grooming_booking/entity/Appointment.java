package com.example.grooming_booking.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private LocalDate date;
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private GroomingService service;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "dog_id")
    private Dog dog;

    public Appointment() {
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public GroomingService getService() {
        return service;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setService(GroomingService service) {
        this.service = service;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}