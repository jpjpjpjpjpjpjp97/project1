package com.una.project1.service;

import com.una.project1.model.Vehicle;
import com.una.project1.repository.VehicleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Transactional
    public Optional<Vehicle> findByBrand(String brand){return vehicleRepository.findByBrand(brand);}
    @Transactional
    public Optional<Vehicle> findByBrandAndModel(String brand, String model) {
        return vehicleRepository.findByBrandAndModel(brand, model);
    }
    @Transactional
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public BindingResult validateCreation(Vehicle vehicle, BindingResult result, String type) {
        //This method will search for a vehicle by its brand and model in the
        // and return an Optional object that may contain the found vehicle or not.
        Optional<Vehicle> optionalVehicle = this.findByBrandAndModel(vehicle.getBrand(), vehicle.getModel());
        if (optionalVehicle.isPresent() && type.equals("create")) {
            result.rejectValue("brand", "error.vehicle", "A vehicle with this brand and model already exists.");
        }
        return result;
    }
    public Vehicle createVehicle(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Vehicle vehicle) {
        vehicleRepository.delete(vehicle);
    }

    public void deleteByBrandAndModel(String brand, String model) {
        vehicleRepository.deleteByBrandAndModel(brand, model);
    }

    public Optional<Vehicle> findById(Long id) {
        return vehicleRepository.findById(id);

    }
}


