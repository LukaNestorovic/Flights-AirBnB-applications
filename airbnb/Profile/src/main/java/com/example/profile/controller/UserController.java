package com.example.profile.controller;


import com.example.profile.model.Role;
import com.example.profile.model.User;
import com.example.profile.model.dto.UpdateProfileDTO;
import com.example.profile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping(path="/{id}")
    @PreAuthorize("hasRole('GUEST') or hasRole('HOST')")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GUEST') or hasRole('HOST')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateRegisteredUser(@RequestBody UpdateProfileDTO newUserInfo) {
        User retVal = userService.update(newUserInfo);
        if(retVal==null){
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(retVal,HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('GUEST') or hasRole('HOST')")
    @GetMapping(path = "/{id}")
    public ResponseEntity<User> findOneById(@PathVariable("id") String id) {
        User user = userService.findOneById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path = "/user/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable("username") String username){
        return new ResponseEntity<>(userService.fidnByUsername(username), HttpStatus.OK);
    }

    @GetMapping(path="/role")
    public ResponseEntity<List<Role>> findAllRole(){
        return new ResponseEntity<>(userService.findAllRole(),HttpStatus.OK);
    }
}