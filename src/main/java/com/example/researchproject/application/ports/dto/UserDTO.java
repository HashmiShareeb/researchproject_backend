package com.example.researchproject.application.ports.dto;

import com.example.researchproject.domain.models.enums.Role;

import java.util.List;

public class UserDTO {
    private String userId;
    private String username;
    //private String password; geen wachtwoord tonen op json response data
    private String email;
    private List<String> roles;

    public UserDTO() {

    }

    public UserDTO(String userId, String username,  String email, List<String> roles) {
        this.userId = userId;
        this.username = username;
        //this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
