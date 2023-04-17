package com.una.project1.controller;

import com.una.project1.model.Payment;
import com.una.project1.model.User;
import com.una.project1.model.Role;
import com.una.project1.service.PaymentService;
import com.una.project1.service.RoleService;
import com.una.project1.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Controller
//@RequestMapping("")
public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/")
    public String mainPage(Authentication authentication, Model model){
        return "main";
    }

    @GetMapping("/auth/register")
    public String registerGetPage(Model model){
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/auth/register")
    public String registerPostPage(@Valid User user, BindingResult result, Model model, @RequestParam("password2") String password2){
        if (!Objects.equals(user.getPasswordHash(), password2)){
            result.rejectValue("passwordHash", "error.user", "Passwords must match.");
        }
        if (result.hasErrors()){
            model.addAttribute("user", user);
            return "auth/register";
        }
        Optional<Role> standardRole = roleService.getByName("StandardClient");
        assert standardRole.isPresent();
        user.addRole(standardRole.get());
        userService.createUser(user);
        return "redirect:/";
    }
    @GetMapping("/auth/login")
    public String loginPage(){
        return "auth/login";
    }
    @GetMapping("/auth/logout")
    public String logoutPage(){
        return "auth/logout";
    }

    @PreAuthorize("hasAuthority('AdministratorClient')")
    @GetMapping("/auth/protected")
    public String protectedPage(Authentication authentication, Model model){
        String username;
        authentication.getAuthorities();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        model.addAttribute("username", username);
        return "auth/protected";
    }
}