package com.example.grooming_booking.controller;

import com.example.grooming_booking.dto.CreateAppointmentRequest;
import com.example.grooming_booking.entity.Appointment;
import com.example.grooming_booking.service.AppointmentService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody CreateAppointmentRequest request) {

        return appointmentService.createAppointment(
                request.getServiceId(),
                request.getName(),
                request.getEmail(),
                request.getPhone(),
                request.getDate(),
                request.getTime()
        );
    }
    @GetMapping("/available")
    public List<LocalTime> getAvailableTimes(@RequestParam String date) {

        LocalDate parsedDate = LocalDate.parse(date);

        return appointmentService.getAvailableTimes(parsedDate);
    }
    @DeleteMapping("/{id}")
    public void cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
    }

}