package com.una.project1.service;

import com.una.project1.model.Insurance;
import com.una.project1.repository.InsuranceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsuranceService {

    @Autowired
    private InsuranceRepository insuranceRepository;
    @Transactional
    public Optional<Insurance> findById(Long id){
        return insuranceRepository.findById(id);
    }
    @Transactional
    public List<Insurance> findAll() {
        return insuranceRepository.findAll();
    }

    @Transactional
    public Optional<Insurance> findByName(String numberPlate) {
        return insuranceRepository.findByName(numberPlate);
    }


    /*
    public Insurance saveInsurance(Insurance insurance) {
        return insuranceRepository.save(insurance);
    }


    public void createInsurance(Insurance insurance) {
        insuranceRepository.save(insurance);
    }


    //agregar update
    public void deleteInsurance(Insurance insurance) {
        insuranceRepository.delete(insurance);
    }

*/





}
