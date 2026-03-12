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
    public Appointment createAppointment(
            @RequestParam Long serviceId,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String date,
            @RequestParam String time
    ){
        return appointmentService.createAppointment(
                serviceId,
                name,
                email,
                phone,
                java.time.LocalDate.parse(date),
                java.time.LocalTime.parse(time));
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

    @PutMapping("/{id}")
    public Appointment updateAppointment(
            @PathVariable Long id,
            @RequestParam Long serviceId,
            @RequestParam String date,
            @RequestParam String time
    ) {

        return appointmentService.updateAppointment(
                id,
                serviceId,
                LocalDate.parse(date),
                LocalTime.parse(time)
        );
    }

}