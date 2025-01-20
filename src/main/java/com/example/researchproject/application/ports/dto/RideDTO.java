package com.example.researchproject.application.ports.dto;

import com.example.researchproject.domain.models.Ride;
import com.example.researchproject.domain.models.enums.RideStatus;

import java.math.BigDecimal;

public class RideDTO {
    private String rideId;
    private String userId;
    private String rideName;
    private String rideDescription;
    private BigDecimal ridePrice;
    private RideStatus rideStatus;

    public RideDTO() {}
    
    public RideDTO(Ride ride) {
        this.rideId = ride.getRideId();
        this.rideName = ride.getRideName();
        this.ridePrice = ride.getRidePrice();
        this.rideDescription = ride.getRideDescription();
        this.userId = ride.getUser().getUserId();
        this.rideStatus = RideStatus.REQUESTED; // Default waarde
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
