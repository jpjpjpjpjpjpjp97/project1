package com.una.project1.service;

import com.una.project1.model.Payment;
import com.una.project1.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Transactional
    public Optional<Payment> getById(Long id){
        return paymentRepository.findById(id);
    }
    @Transactional
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }
}
