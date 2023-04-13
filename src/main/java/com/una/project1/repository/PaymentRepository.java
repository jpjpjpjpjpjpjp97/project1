package com.una.project1.repository;

import com.una.project1.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository  extends JpaRepository<Payment, Long> {
    Optional<Payment> findById(Long id);
}