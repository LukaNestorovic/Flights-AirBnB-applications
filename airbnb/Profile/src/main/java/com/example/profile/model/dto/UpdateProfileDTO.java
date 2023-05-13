package com.example.profile.model.dto;

public class UpdateProfileDTO {
    private String id;

    private String email;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String telephone;

    public UpdateProfileDTO(String id, String email,String name, String surname, String username, String password, String telephone) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
