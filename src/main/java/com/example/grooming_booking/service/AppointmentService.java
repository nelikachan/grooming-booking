package com.example.grooming_booking.service;
import com.example.grooming_booking.entity.GroomingService;
import com.example.grooming_booking.entity.Appointment;
import com.example.grooming_booking.entity.Customer;
import com.example.grooming_booking.entity.GroomingService;
import com.example.grooming_booking.repository.AppointmentRepository;
import com.example.grooming_booking.repository.CustomerRepository;
import com.example.grooming_booking.repository.ServiceRepository;

import java.time.LocalDate;
import java.time.LocalTime;

@GroomingService
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ServiceRepository serviceRepository;
    private final CustomerRepository customerRepository;

    public AppointmentService(
            AppointmentRepository appointmentRepository,
            ServiceRepository serviceRepository,
            CustomerRepository customerRepository) {

        this.appointmentRepository = appointmentRepository;
        this.serviceRepository = serviceRepository;
        this.customerRepository = customerRepository;
    }

    public Appointment createAppointment(
            Long serviceId,
            String name,
            String email,
            String phone,
            LocalDate date,
            LocalTime time
    ) {

        GroomingService service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);

        customerRepository.save(customer);

        Appointment appointment = new Appointment();
        appointment.setService(service);
        appointment.setCustomer(customer);
        appointment.setDate(date);
        appointment.setTime(time);

        return appointmentRepository.save(appointment);
    }
}