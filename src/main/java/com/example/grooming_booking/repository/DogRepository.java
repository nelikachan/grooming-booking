package com.example.grooming_booking.repository;

import com.example.grooming_booking.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Long> {
}
