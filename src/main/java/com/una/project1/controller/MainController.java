package com.una.project1.controller;

import com.una.project1.model.Payment;
import com.una.project1.model.User;
import com.una.project1.model.Role;
import com.una.project1.service.PaymentService;
import com.una.project1.service.RoleService;
import com.una.project1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("")
public class MainController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/")
    public String mainPage(Authentication authentication){
        String username = "";
        Optional<User> user = null;
        Collection<? extends GrantedAuthority> roles = null;
        List<User> all_users = userService.findAll();
        List<Role> all_roles = roleService.findAll();
        List<Payment> all_payments = paymentService.findAll();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
            user = userService.getByUsername(((UserDetails)principal).getUsername());
            roles = ((UserDetails)principal).getAuthorities();
        } else {
            username = principal.toString();
        }
        assert user.isPresent();
        return String.format("<html><body>" +
                        "<div style='margin: 2rem; border: 2px solid black'><b>Username:</b> %s</div>" +
                        "<div style='margin: 2rem; border: 2px solid black'><b>Details:</b> %s</div>" +
                        "<div style='margin: 2rem; border: 2px solid black'><b>Authorities:</b> %s</div>" +
                        "<div style='margin: 2rem; border: 2px solid black'><b>Authenticated:</b> %s</div>" +
                        "<div style='margin: 2rem; border: 2px solid black'><b>Users:</b> %s</div>" +
                        "<div style='margin: 2rem; border: 2px solid black'><b>Roles:</b> %s</div>" +
                        "<div style='margin: 2rem; border: 2px solid black'><b>Payments:</b> %s</div>" +
                        "</body></html>",
            username,
            authentication.getDetails().toString(),
            authentication.getAuthorities().toString(),
            authentication.isAuthenticated(),
            all_users.toString(),
            all_roles.toString(),
            all_payments.toString()
        );
    }

    @GetMapping("/auth/register")
    public String registerPage(){
        return "auth/register";
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
    @GetMapping("/protected")
    public String protectedPage(Authentication authentication){
        String username;
        authentication.getAuthorities();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return "Authorized: "+ username;
    }
}