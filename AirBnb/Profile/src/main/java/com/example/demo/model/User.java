package com.example.demo.model;

import com.example.demo.model.dto.UpdateProfileDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document("users")
public class User {
    @Id
    private String id;

    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
    private String telephone;
    @DBRef
    private Set<Role> roles = new HashSet<>();

    public void update(UpdateProfileDTO updateProfileDTO){
        if(updateProfileDTO.getName() != null) this.name = updateProfileDTO.getName();
        if(updateProfileDTO.getSurname() != null) this.surname = updateProfileDTO.getSurname();
        if(updateProfileDTO.getUsername() != null) this.username = updateProfileDTO.getUsername();
        if(updateProfileDTO.getPassword() != null) this.password = updateProfileDTO.getPassword();
        if(updateProfileDTO.getTelephone() != null) this.telephone = updateProfileDTO.getTelephone();
    }

    public User() {
    }

    public User(String name, String surname, String email, String username,String password, String telephone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.telephone = telephone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
