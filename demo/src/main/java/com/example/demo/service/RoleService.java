package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role findById(String id){
        Role role = roleRepository.findOneById(id);
        return role;
    }

    public List<Role> findByName(String name){
        return roleRepository.findByName(name);
    }

}
