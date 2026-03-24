package com.example.grooming_booking.repository;

import com.example.grooming_booking.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DogRepository extends JpaRepository<Dog, UUID> {
}