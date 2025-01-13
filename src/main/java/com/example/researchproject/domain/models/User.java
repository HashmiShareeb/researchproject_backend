package com.example.researchproject.domain.models;

import jakarta.persistence.*;

import java.util.List;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", nullable = false, unique = true)
    private String UserId;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @ElementCollection
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private List<String> roles;

    protected User() {
    }

    public User(String username, String password, String email, List<String> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

}
