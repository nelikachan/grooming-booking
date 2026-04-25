package com.example.grooming_booking.controller;

import com.example.grooming_booking.dto.CreateAppointmentRequest;
import com.example.grooming_booking.entity.Appointment;
import com.example.grooming_booking.service.AppointmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    @ResponseBody
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
    @ResponseBody
    public List<LocalTime> getAvailableTimes(@RequestParam String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        return appointmentService.getAvailableTimes(parsedDate);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void cancelAppointment(@PathVariable UUID id) {
        appointmentService.cancelAppointment(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Appointment updateAppointment(
            @PathVariable UUID id,
            @RequestParam UUID serviceId,
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
    @GetMapping("/cancel")
    public String cancelByToken(@RequestParam String token) {

        Appointment appointment = appointmentService.findByToken(token);

        appointmentService.cancelAppointment(appointment.getId());

        return "redirect:/cancel.html";
    }

}