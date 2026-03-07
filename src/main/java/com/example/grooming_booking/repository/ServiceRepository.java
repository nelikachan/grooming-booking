package com.example.grooming_booking.repository;

import com.example.grooming_booking.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}