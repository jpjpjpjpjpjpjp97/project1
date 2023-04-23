package com.una.project1.controller;

import com.una.project1.model.Coverage;
import com.una.project1.service.CoverageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/coverage")
public class CoverageController {

    @Autowired
    private CoverageService coverageService;

    @PreAuthorize("hasAuthority('AdministratorClient')")
    @GetMapping("")
    public String coverageList(Model model) {
        List<Coverage> coverages = coverageService.findAll();
        model.addAttribute("coverages", coverages);
        model.addAttribute("coverage", new Coverage());
        return "coverage/list";
    }

    @PreAuthorize("hasAuthority('AdministratorClient')")
    @PostMapping("")
    public String createCoverage(
            Model model,
            @Valid Coverage coverage,
            BindingResult result
    ) {
        List<Coverage> coverages = coverageService.findAll();
        result = coverageService.validateCreation(coverage, result, "create");
        if (result.hasErrors()){
            model.addAttribute("coverages", coverages);
            model.addAttribute("coverage", coverage);
            return "coverage/list";
        }
        coverageService.createCoverage(coverage);
        return "redirect:/coverage";
    }

    @GetMapping("/allCoverages")
    public String showAllCoverages(Model model) {
        List<Coverage> coverages = coverageService.findAll();
        model.addAttribute("coverages", coverages);
        return "coverage/allCoverages";
    }

    @PreAuthorize("hasAuthority('AdministratorClient')")
    @GetMapping("/deleteCoverage/{id}")
    public String deleteCoverage(Model model, @PathVariable("id") Long id) {
        Optional<Coverage> optionalCoverage = coverageService.findById(id);
        if (optionalCoverage.isPresent()) {
            Coverage coverage = optionalCoverage.get();
            coverageService.deleteCoverage(coverage);
        }
        return "redirect:/coverage";
    }
}
