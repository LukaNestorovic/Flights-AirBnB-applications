package com.example.suites.security.services;


import com.example.suites.client.UserServiceClient;
import com.example.suites.model.Role;
import com.example.suites.model.User;
import com.example.suites.repository.RoleRepository;
import com.example.suites.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    private final WebClient webClient;

    public UserDetailsServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user1 = webClient.get()
                .uri("http://localhost:8085/api/profile/user/{username}", username)
                .retrieve()
                .bodyToMono(User.class)
                .block();
        userRepository.save(user1);



        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));


        System.out.println(user.getUsername());
        return UserDetailsImpl.build(user);
    }

}
