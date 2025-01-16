package com.example.researchproject.application.ports.dto;

import java.util.List;

public class AssignRoleDTO {
    private String username;
    private List<String> roles;

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
