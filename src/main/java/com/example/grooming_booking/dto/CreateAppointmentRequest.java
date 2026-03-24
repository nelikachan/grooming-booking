package com.example.grooming_booking.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class CreateAppointmentRequest {

    private UUID serviceId;
    private String name;
    private String email;
    private String phone;
    private LocalDate date;
    private LocalTime time;

    public UUID getServiceId() { return serviceId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public LocalDate getDate() { return date; }
    public LocalTime getTime() { return time; }
}