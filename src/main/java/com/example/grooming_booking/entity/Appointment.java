package com.example.grooming_booking.entity;
import com.example.grooming_booking.enums.AppointmentStatus;
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

    @Column(unique = true)
    private String confirmationToken;

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
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
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
    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    @Column(length = 500)
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}