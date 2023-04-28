package com.una.project1.controller;

import com.una.project1.model.Vehicle;
import com.una.project1.service.VehicleService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
            BindingResult result,
            @RequestParam("image") MultipartFile file
    ) throws IOException {
        List<Vehicle> vehicles = vehicleService.findAll();
        result = vehicleService.validateCreation(vehicle, result, "create");
        if (result.hasErrors()){
            model.addAttribute("vehicles", vehicles);
            model.addAttribute("vehicle", vehicle);
            return "vehicle/list";
        }
        vehicleService.createVehicle(vehicle, file);
        return "redirect:/vehicle";
    }
    @GetMapping("/image/{id}")
    public ResponseEntity<InputStreamResource> showVehicleImage(@PathVariable String id) {
        InputStream is = new ByteArrayInputStream(vehicleService.getImage(Long.valueOf(id)));
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(is));
    }
    @PreAuthorize("hasAuthority('AdministratorClient')")
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







