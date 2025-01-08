package com.example.researchproject.domain.models;

import com.example.researchproject.domain.models.enums.RideStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
@Table(name = "rides")
public class Rides {

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


    public Rides(String rideName, RideStatus rideStatus, BigDecimal ridePrice) {
        this.rideName = rideName;
        this.rideStatus = rideStatus;
        this.ridePrice = ridePrice;
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

    // Default constructor (required by Hibernate) - protected so that it's not callable from outside
    protected Rides() {
    }

    @Override
    public String toString() {
        return "Rides{" +
                "rideId=" + rideId +
                ", rideName='" + rideName + '\'' +
                ", rideStatus=" + rideStatus +
                ", ridePrice=" + ridePrice +
                '}';
    }
}
