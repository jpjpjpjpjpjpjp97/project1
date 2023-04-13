package com.una.project1.service;

import com.una.project1.model.Role;
import com.una.project1.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Transactional
    public Optional<Role> getByName(String name){
        return roleRepository.findByName(name);
    }
    @Transactional
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
