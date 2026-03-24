package com.example.grooming_booking.repository;

import com.example.grooming_booking.entity.GroomingService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceRepository extends JpaRepository<GroomingService, UUID> {
}