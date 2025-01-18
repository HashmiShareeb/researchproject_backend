package com.example.researchproject.domain.models;

import com.example.researchproject.domain.models.enums.RideStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "rides")
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ride_id", nullable = false)
    private String rideId;

    @Column(name = "ride_name", nullable = false)
    private String rideName;

    @Enumerated(EnumType.STRING)
    @Column(name = "ride_status", nullable = false)
    private RideStatus rideStatus = RideStatus.REQUESTED; // Default value bj alle ritjes

    @Column(name = "ride_price", nullable = false)
    private BigDecimal ridePrice;

    @Column(name = "ride_description", nullable = true)
    private String rideDescription;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @PrePersist // Before inserting a new record, set the creation date to the current date and time
    public void setDefaultCreatedAt() {
        // Set the creation date to the current date and time if it's not set
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }


    public Ride(String rideName, RideStatus rideStatus, BigDecimal ridePrice, String rideDescription, LocalDateTime createdAt, User user, Vehicle vehicle) {
        this.rideName = rideName;
        this.rideStatus = rideStatus;
        this.ridePrice = ridePrice;
        this.rideDescription = rideDescription;
        this.createdAt = createdAt;
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

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }

    public BigDecimal getRidePrice() {
        return ridePrice;
    }

    public void setRidePrice(BigDecimal ridePrice) {
        this.ridePrice = ridePrice;
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

    // Default constructor (required by Hibernate) - protected so that it's not callable from outside
    public Ride() {
    }

    @Override
    public String toString() {
        return "Rides{" +
                "rideId=" + rideId +
                ", rideName='" + rideName + '\'' +
                ", rideStatus=" + rideStatus +
                ", ridePrice=" + ridePrice +
                ", rideDescription='" + rideDescription + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }

    public User getUser() {
        return user;
    }
    public String getUsername() {
        if (user != null) {
            return user.getUsername();
        } else {
            // Handle the case where user is null
            return null;
        }
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

}
