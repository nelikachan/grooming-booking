package com.example.grooming_booking.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateAppointmentRequest {

    private Long serviceId;

    private String name;

    private String email;

    private String phone;

    private LocalDate date;

    private LocalTime time;
}