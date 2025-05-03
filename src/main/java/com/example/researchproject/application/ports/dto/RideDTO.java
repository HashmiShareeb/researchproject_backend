package com.example.researchproject.application.ports.dto;

import com.example.researchproject.domain.models.Location.Location;
import com.example.researchproject.domain.models.Ride.Ride;
import com.example.researchproject.domain.models.Ride.RideStatus;
import com.example.researchproject.domain.models.User.Role;

import java.math.BigDecimal;
import java.util.stream.Collectors;

//set how response should look like
public class RideDTO {
    private String rideId;
    private String rideName;
    private String rideDescription;
    private BigDecimal ridePrice;
    private RideStatus rideStatus;
    private VehicleDTO vehicle;
    private UserDTO user;

    private String routeSummary; //dynamic route naam
    private Location pickupLocation;
    private Location dropoffLocation;

    public RideDTO() {}

    public RideDTO(Ride ride) {
        this.rideId = ride.getRideId();
        this.rideName = ride.getRideName();
        this.ridePrice = ride.getRidePrice();
        this.rideDescription = ride.getRideDescription();
        this.rideStatus = RideStatus.REQUESTED; // Default waarde

        this.pickupLocation = ride.getPickupLocation();
        this.dropoffLocation = ride.getDropoffLocation();

        this.routeSummary = generateRouteSummary();
        this.user = new UserDTO(
                ride.getUser().getUserId(),
                ride.getUser().getUsername(),
                ride.getUser().getEmail(),
                ride.getUser().getRoles().stream().map(Role::name).collect(Collectors.toList())
        );
        this.vehicle = new VehicleDTO(
                ride.getVehicle().getVehicleId(),
                ride.getVehicle().getManufacturer(),
                ride.getVehicle().getModel(),
                ride.getVehicle().getLicensePlate(),
                ride.getVehicle().getBatteryLevel(),
                ride.getVehicle().getVehicleStatus()
        );


    }

    private String generateRouteSummary() {
        if (pickupLocation != null && dropoffLocation != null) {
            return pickupLocation.getAddress() + " → " + dropoffLocation.getAddress();
        }
        return "Route";
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


    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }

    public String getRouteSummary() {
        return routeSummary;
    }

    public void setRouteSummary(String routeSummary) {
        this.routeSummary = routeSummary;
    }

    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public void setDropoffLocation(Location dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }
}
