package com.una.project1.service;

import com.una.project1.model.Insurance;
import com.una.project1.model.Payment;
import com.una.project1.model.User;
import com.una.project1.model.Vehicle;
import com.una.project1.repository.InsuranceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

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
    public  List<Insurance> findAll() {
        return insuranceRepository.findAll();
    }

    @Transactional
    public Optional<Insurance> findByNumberPlate(String numberPlate) {
        return insuranceRepository.findByNumberPlate(numberPlate);
    }

    public BindingResult validateCreation(Insurance insurance, BindingResult result, String type) {
        //This method will search for a vehicle by its brand and model in the
        // and return an Optional object that may contain the found vehicle or not.
        Optional<Insurance> optionalVehicle = this.findByNumberPlate(insurance.getNumberPlate());
        if (optionalVehicle.isPresent() && type.equals("create")) {
            result.rejectValue("numberPlate", "error.Insurance", "A insurance wit this numberPlate already exists.");
        }
        return result;
    }
    public Insurance assignUser(Insurance insurance, User user) {
        insurance.setUser(user);
        return insurance;
    }

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







}
