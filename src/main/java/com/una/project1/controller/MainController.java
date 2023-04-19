package com.una.project1.controller;

import com.una.project1.model.Payment;
import com.una.project1.model.User;
import com.una.project1.form.UserRegisterHelper;
import com.una.project1.service.PaymentService;
import com.una.project1.service.RoleService;
import com.una.project1.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/")
    public String main(Authentication authentication, Model model){
        return "main";
    }
    @GetMapping("/auth/register")
    public String registerGet(Model model){
        model.addAttribute("userRegisterHelper", new UserRegisterHelper());
        return "auth/register";
    }
    @PostMapping("/auth/register")
    public String registerPost(
            @Valid UserRegisterHelper userRegisterHelper,
            BindingResult result,
            Model model
    ) {
        User user = new User(
            userRegisterHelper.getName(),
            userRegisterHelper.getUsername(),
            userRegisterHelper.getPasswordHash(),
            userRegisterHelper.getPhoneNumber(),
            userRegisterHelper.getEmail()
        );
        Payment payment = new Payment(
                userRegisterHelper.getNumber(),
                userRegisterHelper.getOwner(),
                userRegisterHelper.getExpirationDate(),
                userRegisterHelper.getSecurityCode(),
                userRegisterHelper.getBillingAddress()
        );
        result = userService.validateCreation(user, userRegisterHelper.getPassword2(), result, "create");
        result = paymentService.validateCreation(payment, result);
        if (result.hasErrors()){
            model.addAttribute("userRegisterHelper", userRegisterHelper);
            return "auth/register";
        }
        user = userService.assignRole(user, "StandardClient");
        user = userService.createUser(user);
        payment = paymentService.assignUser(payment, user);
        payment = paymentService.savePayment(payment);
        return "redirect:/";
    }
    @GetMapping("/auth/login")
    public String login(){
        return "auth/login";
    }
    @GetMapping("/auth/logout")
    public String logout(){
        return "auth/logout";
    }
    @PreAuthorize("hasAuthority('AdministratorClient')")
    @GetMapping("/auth/protected")
    public String protectedPage(Authentication authentication, Model model){
        return "auth/protected";
    }
}