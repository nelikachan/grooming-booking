package com.example.grooming_booking.controller;

import com.example.grooming_booking.entity.Appointment;
import com.example.grooming_booking.entity.GroomingService;
import com.example.grooming_booking.repository.ServiceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private final ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GetMapping
    public List<GroomingService> getAll() {
        return serviceRepository.findAll();
    }
}
