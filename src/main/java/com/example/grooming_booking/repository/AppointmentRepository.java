package com.example.grooming_booking.repository;

import com.example.grooming_booking.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}