package com.example.profile.service;

import com.example.profile.model.User;
import com.example.profile.model.dto.UpdateProfileDTO;
import com.example.profile.repository.UserRepository;
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
