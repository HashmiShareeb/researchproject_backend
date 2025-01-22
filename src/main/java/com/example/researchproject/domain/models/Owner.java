package com.example.researchproject.domain.models;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.*;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Generates UUID
    @Column(name = "owner_id", nullable = false, unique = true)
    private String ownerId;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    // @OneToMany(mappedBy = "owner"): Specifies that one Owner can have many Ride entities. The mappedBy attribute indicates that the owner field in the Ride entity owns the relationship.
    // cascade = CascadeType.ALL: Any operation (persist, merge, remove, etc.) performed on the Owner entity will be cascaded to the associated Ride entities.
    // orphanRemoval = true: If a Ride entity is removed from the Owner's collection, it will be deleted from the database.
    // This setup ensures that the Owner entity manages the lifecycle of its associated Ride entities.

    //@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true) //one-to-many: owner is parent
    //private List<Ride> ride;

    //@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonManagedReference // prevent json elements for looping infinitely
    //private List<Vehicle> vehicle;


    public Owner(String ownerId, String firstName, String lastName, String email, String phoneNumber) {
        this.ownerId = ownerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;

        //this.vehicle = vehicle;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    protected Owner(){

    }


    @Override
    public String toString() {
        return "Owner{" +
                "ownerId=" + ownerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }



}
