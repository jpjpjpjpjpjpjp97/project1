package com.una.project1.preload;


import com.una.project1.model.Payment;
import com.una.project1.model.PaymentSchedule;
import com.una.project1.model.Role;
import com.una.project1.model.User;
import com.una.project1.model.Vehicle;
import com.una.project1.repository.PaymentRepository;
import com.una.project1.repository.PaymentScheduleRepository;
import com.una.project1.repository.RoleRepository;
import com.una.project1.repository.UserRepository;
import com.una.project1.repository.VehicleRepository;
import com.una.project1.service.UserService;
import com.una.project1.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Set;

@Configuration
class LoadData {
    private static final Logger log = LoggerFactory.getLogger(LoadData.class);

    @Autowired
    private UserService userService;
    private VehicleService vehicleService;
    @Bean
    CommandLineRunner initDatabase(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PaymentRepository paymentRepository,
            PaymentScheduleRepository paymentScheduleRepository,
            VehicleRepository VehicleRepository

    ) {
        return args -> {
            log.info("Loading Roles...");
            Set<Role> adminRoleSet = new java.util.HashSet<>(Collections.emptySet());
            Set<Role> userRoleSet = new java.util.HashSet<>(Collections.emptySet());
            Role adminClient = roleRepository.save(new Role("AdministratorClient"));
            Role standardClient = roleRepository.save(new Role("StandardClient"));
            adminRoleSet.add(adminClient);
            userRoleSet.add(standardClient);
            log.info("Loading Users...");
            User user1 = userService.createUser(new User("jdiaz", "Jose","12345", adminRoleSet));
            User user2 = userService.createUser(new User("elopez", "Eliecer","12345", userRoleSet));
            userRepository.save(user1);
            userRepository.save(user2);
            log.info("Loading Payments...");
            Payment payment1 = new Payment("1234123456785678","Jose Diaz", "04/24", "123", "Heredia, Costa Rica", user1);
            Payment payment2 = new Payment("9876543219876543","Eliecer Lopez", "10/24", "456", "New York, United States", user2);
            paymentRepository.save(payment1);
            paymentRepository.save(payment2);
            PaymentSchedule quarterlyPayment = new PaymentSchedule("Quarterly");
            PaymentSchedule biannualPayment = new PaymentSchedule("Biannual");
            PaymentSchedule yearlyPayment = new PaymentSchedule("Yearly");
            paymentScheduleRepository.save(quarterlyPayment);
            paymentScheduleRepository.save(biannualPayment);
            paymentScheduleRepository.save(yearlyPayment);
            log.info("Loading Vehicles...");
            Vehicle vehicle1 = new Vehicle(null, "Toyota", "Corolla");
            Vehicle vehicle2 = new Vehicle(null, "Honda", "Civic");
            Vehicle vehicle3 = new Vehicle(null, "Ford", "Mustang");
            Vehicle vehicle4 = new Vehicle(null, "Nissan", "Sentra");
            Vehicle vehicle5 = new Vehicle(null, "Chevrolet", "Camaro");
            VehicleRepository.save(vehicle1);
            VehicleRepository.save(vehicle2);
            VehicleRepository.save(vehicle3);
            VehicleRepository.save(vehicle4);
            VehicleRepository.save(vehicle5);

        };
    }
}