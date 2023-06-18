package com.example.reservations.security.services;

import com.example.reservations.model.User;
import com.example.reservations.repository.RoleRepository;
import com.example.reservations.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

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
                .uri("http://apigateway-docker:8085/api/profile/user/{username}", username)
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

