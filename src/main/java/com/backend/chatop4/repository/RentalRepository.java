package com.backend.chatop4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.chatop4.model.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long> {
  
  
}
