package com.example.profile.service;

import com.example.profile.model.Role;
import com.example.profile.model.User;
import com.example.profile.model.dto.UpdateProfileDTO;
import com.example.profile.repository.RoleRepository;
import com.example.profile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAllRole(){
        return roleRepository.findAll();
    }

    public List<User> findAll(){
        List<User> users = userRepository.findAll();
        return users;
    }

    public User delete(String id){
        User user = userRepository.findOneById(id);
        userRepository.delete(user);
        return user;
    }

    public User update(UpdateProfileDTO updateProfileDTO){
        User user = userRepository.findOneByEmail(updateProfileDTO.getEmail());
        user.update(updateProfileDTO);
        return userRepository.save(user);
    }

    public User findOneById(String id){
        return userRepository.findOneById(id);
    }

    public User fidnByUsername(String username) { return userRepository.findOneByUsername(username); }

}
