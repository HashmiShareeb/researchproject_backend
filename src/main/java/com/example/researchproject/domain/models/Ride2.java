package com.example.researchproject.domain.models;

import com.example.researchproject.domain.models.enums.RideStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "rides")
public class Ride2 {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ride_id", nullable = false)
    private String rideId;

    @Column(name = "ride_name", nullable = false)
    private String rideName;

    @Column(name = "ride_price", nullable = false)
    private BigDecimal ridePrice;

    @Column(name = "ride_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    @Column(name = "ride_description", nullable = true)
    private String rideDescription;

    @Column(name = "created_at", nullable = true)
    private LocalDateTime createdAt = LocalDateTime.now(); // Default value

    @Embedded  // Embeds the Location object into the same table
    private Location location;

     @ManyToOne(fetch = FetchType.EAGER)  // Many rides can belong to one user
    @JoinColumn(name = "user_id", nullable = false) // Foreign key column in rides table
    //@JsonBackReference
    @JsonIgnoreProperties({"rides"})
    private User user;

    // 🚗 Vehicle entiteit
     @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_id", nullable = false) // Foreign Key to Vehicle
    private Vehicle vehicle;

    public Ride2() {
    }

    public Ride2(String rideName, RideStatus rideStatus, BigDecimal ridePrice, String rideDescription, LocalDateTime createdAt, Location location, User user, Vehicle vehicle) {
        this.rideName = rideName;
        this.rideStatus = rideStatus;
        this.ridePrice = ridePrice;
        this.rideDescription = rideDescription;
        this.createdAt = createdAt;
        this.location = location;
        this.user = user;
        this.vehicle = vehicle;
    }

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public String getRideName() {
        return rideName;
    }

    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    public BigDecimal getRidePrice() {
        return ridePrice;
    }

    public void setRidePrice(BigDecimal ridePrice) {
        this.ridePrice = ridePrice;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }

    public String getRideDescription() {
        return rideDescription;
    }

    public void setRideDescription(String rideDescription) {
        this.rideDescription = rideDescription;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Ride2{" +
                "rideId='" + rideId + '\'' +
                ", rideName='" + rideName + '\'' +
                ", ridePrice=" + ridePrice +
                ", rideStatus=" + rideStatus +
                ", rideDescription='" + rideDescription + '\'' +
                ", createdAt=" + createdAt +
                ", location=" + location +
                ", user=" + user +
                ", vehicle=" + vehicle +
                '}';
    }



}
