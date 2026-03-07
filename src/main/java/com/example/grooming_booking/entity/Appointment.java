package com.example.grooming_booking.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "service_id",nullable = false)
    private GroomingService service;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Appointment() {
    }

    public Long getId() {
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