package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.dto.UpdateProfileDTO;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

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

}
