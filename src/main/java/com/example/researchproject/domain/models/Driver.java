package com.example.researchproject.domain.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;



public class Driver {

    private UUID driverId = UUID.randomUUID(); //rnd uuid
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private String phoneNumber;
    private String email;
    private LocalDateTime createdAt;
    @Embedded
    private Location location;

    public Driver(UUID driverId, String firstname, String lastname, String licenseNumber, String phone, LocalDateTime createdAt, Location location) {
        this.driverId = driverId;
        this.firstName = firstname;
        this.lastName = lastname;
        this.licenseNumber = licenseNumber;
        this.phoneNumber = phone;
        this.createdAt = createdAt;
        this.location= location;
    }

    public UUID getDriverId() {
        return driverId;
    }

    public void setDriverId(UUID driverId) {
        this.driverId = driverId;
    }

    public String getFirstName() {
        return firstName + " " + lastName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public void setLastName() {
        this.lastName = lastName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getPhone() {
        return phoneNumber;
    }

    public void setPhone(String phone) {
        this.phoneNumber = phone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
