package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

/*    public User create(User user){
        User newUser = this.userRepository.save(user);
        return newUser;
    }*/

    public User save(UserDTO userDTO){
        User u = new User();
        u.setEmail(userDTO.getEmail());
        u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        u.setName(userDTO.getName());
        u.setSurname(userDTO.getSurname());
        u.setEnabled(true);
        List<Role> roles = roleService.findByName("ROLE_USER");
        u.setRoles(roles);

        return userRepository.save(u);
    }

    public List<User> findAll(){
        List<User> users = userRepository.findAll();
        return users;
    }

    public User findOneByEmail(String email){
        User user = userRepository.findOneByEmail(email);
        return user;
    }
}
