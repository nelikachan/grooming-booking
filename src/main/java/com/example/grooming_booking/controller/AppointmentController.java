package com.example.grooming_booking.controller;

import com.example.grooming_booking.dto.CreateAppointmentRequest;
import com.example.grooming_booking.entity.Appointment;
import com.example.grooming_booking.service.AppointmentService;
import jakarta.mail.MessagingException;
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
    public Appointment createAppointment(@RequestBody CreateAppointmentRequest request) throws MessagingException {
        return appointmentService.createAppointment(
                request.getServiceId(),
                request.getName(),
                request.getEmail(),
                request.getPhone(),
                request.getDate(),
                request.getTime(),
                request.getComment()
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
    @GetMapping("/all")
    @ResponseBody

    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }
    @GetMapping("/by-month")
    @ResponseBody
    public List<Appointment> getByMonth(
            @RequestParam int year,
            @RequestParam int month
    ) {
        return appointmentService.getAppointmentsByMonth(year, month);
    }

    }

