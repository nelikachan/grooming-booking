package com.example.grooming_booking.service;

import com.example.grooming_booking.entity.Appointment;
import com.example.grooming_booking.entity.Customer;
import com.example.grooming_booking.entity.GroomingService;

import com.example.grooming_booking.repository.AppointmentRepository;
import com.example.grooming_booking.repository.CustomerRepository;
import com.example.grooming_booking.repository.ServiceRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ServiceRepository serviceRepository;
    private final CustomerRepository customerRepository;
    private final EmailService emailService;

    public AppointmentService(
            AppointmentRepository appointmentRepository,
            ServiceRepository serviceRepository,
            CustomerRepository customerRepository,
            EmailService emailService) {

        this.appointmentRepository = appointmentRepository;
        this.serviceRepository = serviceRepository;
        this.customerRepository = customerRepository;
        this.emailService = emailService;
    }

    public Appointment createAppointment(
            Long serviceId,
            String name,
            String email,
            String phone,
            LocalDate date,
            LocalTime time
    ) {

        if (appointmentRepository.existsByDateAndTime(date, time)) {
            throw new RuntimeException("This time slot is already booked");
        }

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

        Appointment savedAppointment = appointmentRepository.save(appointment);

        emailService.sendAppointmentConfirmation(
                email,
                date.toString(),
                time.toString(),
                service.getName()
        );

        return savedAppointment;
    }
    public List<LocalTime> getAvailableTimes(LocalDate date) {

        List<LocalTime> workingHours = List.of(
                LocalTime.of(9,0),
                LocalTime.of(10,0),
                LocalTime.of(11,0),
                LocalTime.of(12,0),
                LocalTime.of(13,0),
                LocalTime.of(14,0),
                LocalTime.of(15,0),
                LocalTime.of(16,0),
                LocalTime.of(17,0)
        );

        List<Appointment> appointments = appointmentRepository.findByDate(date);

        List<LocalTime> bookedTimes = appointments.stream()
                .map(Appointment::getTime)
                .toList();

        return workingHours.stream()
                .filter(time -> !bookedTimes.contains(time))
                .toList();
    }



}