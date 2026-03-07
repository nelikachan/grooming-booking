package com.example.grooming_booking.repository;

import com.example.grooming_booking.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {



    List<Appointment> findByDate(LocalDate date);
    boolean existsByDateAndTime(LocalDate date, LocalTime time);}
