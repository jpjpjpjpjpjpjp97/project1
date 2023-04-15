package com.una.project1.service;

import com.una.project1.model.User;
import com.una.project1.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    public User createUser(User user){
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(user);
    }
    @Transactional
    public Optional<User> getByUsername(String username){
        return userRepository.findByUsername(username);
    }
    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
