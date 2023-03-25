package com.example.demo.model.dto;

public class LogInDTO {
    String id;
    String password;

    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LogInDTO() {
    }

    public LogInDTO(String id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }
}
