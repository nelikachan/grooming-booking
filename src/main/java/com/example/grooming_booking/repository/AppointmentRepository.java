package com.example.grooming_booking.repository;

import com.example.grooming_booking.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    List<Appointment> findByDate(LocalDate date);

    boolean existsByDateAndTime(LocalDate date, LocalTime time);

    Optional<Appointment> findByConfirmationToken(String token);
}