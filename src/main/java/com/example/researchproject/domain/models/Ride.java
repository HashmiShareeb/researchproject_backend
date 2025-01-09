package com.example.researchproject.domain.models;

import com.example.researchproject.domain.models.enums.RideStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "rides")
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    @Column(name = "ride_id", nullable = false)
    private Long rideId;

    @Column(name = "ride_name", nullable = false)
    private String rideName;

    @Enumerated(EnumType.STRING)
    @Column(name = "ride_status", nullable = false)
    private RideStatus rideStatus;

    @Column(name = "ride_price", nullable = false)
    private BigDecimal ridePrice;

    @Column(name = "ride_description", nullable = true)
    private String rideDescription;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist // Before inserting a new record, set the creation date to the current date and time
    public void setDefaultCreatedAt() {
        // Set the creation date to the current date and time if it's not set
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }


    public Ride(String rideName, RideStatus rideStatus, BigDecimal ridePrice, String rideDescription, LocalDateTime createdAt) {
        this.rideName = rideName;
        this.rideStatus = rideStatus;
        this.ridePrice = ridePrice;
        this.rideDescription = rideDescription;
        this.createdAt = createdAt;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
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
    protected Ride() {
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
}
