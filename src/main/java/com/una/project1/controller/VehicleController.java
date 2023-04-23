package com.una.project1.controller;

import com.una.project1.model.Vehicle;
import com.una.project1.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PreAuthorize("hasAuthority('AdministratorClient')")
    @GetMapping("")
    public String vehicleList(
            Model model
    ) {
        List<Vehicle> vehicles = vehicleService.findAll();
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("vehicle", new Vehicle());
        return "vehicle/list";
    }
    @PreAuthorize("hasAuthority('AdministratorClient')")
    @PostMapping("")
    public String createVehicle(
            Model model,
            @Valid Vehicle vehicle,
            BindingResult result
    ){
        List<Vehicle> vehicles = vehicleService.findAll();
        result = vehicleService.validateCreation(vehicle, result, "create");
        if (result.hasErrors()){
            model.addAttribute("vehicles", vehicles);
            model.addAttribute("vehicle", vehicle);
            return "vehicle/list";
        }
        vehicleService.createVehicle(vehicle);
        return "redirect:/vehicle";
    }

    @GetMapping("/allVechicles")
    public String showAllVehicles(Model model) {
        List<Vehicle> vehicles = vehicleService.findAll();
        model.addAttribute("vehicles", vehicles);
        return "vehicle/allVehicles";
    }
    @GetMapping("/deleteVehicle/{id}")
    public String deleteVehicle(Model model, @PathVariable("id") Long id) {
        Optional<Vehicle> optionalVehicle = vehicleService.findById(id);
        if (optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();
            vehicleService.deleteVehicle(vehicle);
        }
        return "redirect:/vehicle";
    }








}







