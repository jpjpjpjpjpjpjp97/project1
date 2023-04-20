package com.una.project1.repository;

import com.una.project1.model.PaymentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentScheduleRepository extends JpaRepository<PaymentSchedule, Long> {
    Optional<PaymentSchedule> findByName(String name);
    Optional<PaymentSchedule> findById(Long id);}