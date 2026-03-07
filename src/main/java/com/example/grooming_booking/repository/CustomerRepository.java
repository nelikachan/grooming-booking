package com.example.grooming_booking.repository;

import com.example.grooming_booking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}