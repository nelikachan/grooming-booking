package com.example.grooming_booking.service;

import com.example.grooming_booking.entity.Appointment;
import com.example.grooming_booking.entity.Customer;
import com.example.grooming_booking.entity.GroomingService;
import com.example.grooming_booking.repository.AppointmentRepository;
import com.example.grooming_booking.repository.CustomerRepository;
import com.example.grooming_booking.repository.ServiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService {
    @Value("${app.base-url}")
    private String baseUrl;
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
            UUID serviceId,
            String name,
            String email,
            String phone,
            LocalDate date,
            LocalTime time
    ) {
        if (date.isBefore(LocalDate.now()) ||
                (date.equals(LocalDate.now()) && time.isBefore(LocalTime.now()))) {

            throw new RuntimeException("Cannot book appointment in the past");
        }

        if (appointmentRepository.existsByDateAndTime(date, time)) {
            throw new RuntimeException("This time slot is already booked");
        }

        GroomingService service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        Customer customer = customerRepository.findByEmail(email)
                .orElseGet(() -> {
                    Customer newCustomer = new Customer();
                    newCustomer.setName(name);
                    newCustomer.setEmail(email);
                    newCustomer.setPhone(phone);
                    return customerRepository.save(newCustomer);
                });

        Appointment appointment = new Appointment();
        appointment.setConfirmationToken(UUID.randomUUID().toString());
        appointment.setService(service);
        appointment.setCustomer(customer);
        appointment.setDate(date);
        appointment.setTime(time);

        Appointment savedAppointment = appointmentRepository.save(appointment);
        String token = savedAppointment.getConfirmationToken();
        String cancelLink = baseUrl + "/appointments/cancel?token=" + token;
        String editLink = baseUrl + "/appointments/edit?token=" + token;

        emailService.sendAppointmentConfirmation(
                email,
                date.toString(),
                time.toString(),
                service.getName(),
                cancelLink,
                editLink
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

    public void cancelAppointment(UUID id) {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        String email = appointment.getCustomer().getEmail();
        String date = appointment.getDate().toString();
        String time = appointment.getTime().toString();
        String service = appointment.getService().getName();

        appointmentRepository.delete(appointment);

        emailService.sendAppointmentCancelled(email, date, time, service);
    }

    public Appointment updateAppointment(
            UUID id,
            UUID serviceId,
            LocalDate date,
            LocalTime time
    ) {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        if (date.isBefore(LocalDate.now()) ||
                (date.equals(LocalDate.now()) && time.isBefore(LocalTime.now()))) {

            throw new RuntimeException("Cannot book appointment in the past");
        }

        if (appointmentRepository.existsByDateAndTime(date, time)) {
            throw new RuntimeException("This time slot is already booked");
        }

        GroomingService service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        appointment.setDate(date);
        appointment.setTime(time);
        appointment.setService(service);

        Appointment updatedAppointment = appointmentRepository.save(appointment);

        emailService.sendAppointmentUpdated(
                appointment.getCustomer().getEmail(),
                date.toString(),
                time.toString(),
                service.getName()
        );

        return updatedAppointment;}
    public Appointment findByToken(String token) {
        return appointmentRepository.findByConfirmationToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));
    }
}