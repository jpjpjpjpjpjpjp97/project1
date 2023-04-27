package com.una.project1.controller;

import com.una.project1.model.Insurance;
import com.una.project1.model.User;
import com.una.project1.service.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
@RequestMapping("/insurance")
public class InsuranceController {
    @Autowired
    private InsuranceService insuranceService;
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String insuranceList(
            Model model
    ){
        List<User> users = userService.findAll();
        List<Insurance> insurances = insuranceService.findAll();
        model.addAttribute("insurances", insurances);
        model.addAttribute("users", users);
        model.addAttribute("insurance", new Insurance());
        return "insurance/list";
    }

    @PostMapping("")
    public String insurancesCreate(
            Model model,
            @Valid Insurance insurance,
            BindingResult result
    ){
        List<User> users = userService.findAll();
        List<Insurance> insurances = insuranceService.findAll();

        result = insuranceService.validateCreation(insurance, result, "create");
        if (result.hasErrors()){

            model.addAttribute("insurances", insurances);
            model.addAttribute("users", users);
            model.addAttribute("insurance", insurance);
            return "insurance/list";
        }
        insuranceService.createInsurance(insurance);
        return "redirect:/insurance";
    }

    @GetMapping("/allInsurance")
    public String showAllInsurance(Model model) {
        List<Insurance> insurances = insuranceService.findAll();
        model.addAttribute("insurances", insurances);
        return "insurance/allInsurance";
    }

    @GetMapping("/deleteInsurance/{id}")
    public String deleteInsurance(Model model, @PathVariable("id") Long id) {
        Optional<Insurance> optionalInsurance = insuranceService.findById(id);
        if (optionalInsurance.isPresent()) {
            Insurance insurance = optionalInsurance.get();
            insuranceService.deleteInsurance(insurance);
        }
        return "redirect:/insurance";
    }

    @PreAuthorize("isSelfOrAdmin(#numberPlate)")
    @GetMapping("/{numberPlate}")
    public String userDetail(
            Model model,
            @PathVariable("numberPlate") String numberPlate
    ){
        Optional<Insurance> insurance = insuranceService.findByNumberPlate(numberPlate);
        if (!insurance.isPresent()){
            return "404";
        }
        model.addAttribute("insurance",insurance.get());
        return "insurance/detail";
    }





}