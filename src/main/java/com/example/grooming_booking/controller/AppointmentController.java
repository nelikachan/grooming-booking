package com.example.grooming_booking.controller;

import com.example.grooming_booking.entity.Appointment;
import com.example.grooming_booking.service.AppointmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public Appointment createAppointment(
            @RequestParam Long serviceId,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String date,
            @RequestParam String time
    ) {

        return appointmentService.createAppointment(
                serviceId,
                name,
                email,
                phone,
                java.time.LocalDate.parse(date),
                java.time.LocalTime.parse(time)
        );
    }
}