package com.una.project1.repository;

import com.una.project1.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    Optional<Insurance> findByName(String numberPlate);
    Optional<Insurance> findById(Long id);}