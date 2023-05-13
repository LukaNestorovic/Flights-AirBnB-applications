package com.example.profile.model.dto;

public class LogInDTO {
    String password;

    String username;

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

    public LogInDTO() {
    }

    public LogInDTO(String password, String username) {
        this.password = password;
        this.username = username;
    }
}
