package com.example.researchproject.domain.models.Ride;

import com.example.researchproject.domain.models.Location.Location;
import com.example.researchproject.domain.models.User.User;
import com.example.researchproject.domain.models.Vehicle.Vehicle;
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

    @Column(name = "ride_price", nullable = false)
    private BigDecimal ridePrice;

    @Column(name = "ride_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    @Column(name = "ride_description", nullable = true)
    private String rideDescription;

    @Column(name = "created_at", nullable = true, updatable = false)
    private LocalDateTime createdAt;

    //onCreate method to be executed before the entity is persisted
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // 🏁 Locatie entiteit
    //@Embedded  // Embeds the Location object into the same table
    //private Location location;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "pickup_address")),
            @AttributeOverride(name = "city", column = @Column(name = "pickup_city")),
            @AttributeOverride(name = "latitude", column = @Column(name = "pickup_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "pickup_longitude"))
    })
    private Location pickupLocation;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "dropoff_address")),
            @AttributeOverride(name = "city", column = @Column(name = "dropoff_city")),
            @AttributeOverride(name = "latitude", column = @Column(name = "dropoff_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "dropoff_longitude"))
    })
    private Location dropoffLocation;


    @ManyToOne(fetch = FetchType.EAGER)  // Many rides can belong to one user
    @JoinColumn(name = "user_id", nullable = false) // Foreign key column in rides table
    private User user;

    // 🚗 Vehicle entiteit join
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_id", nullable = true) // Foreign Key to Vehicle
    private Vehicle vehicle;

    public Ride() {
    }

    public Ride(String rideName, RideStatus rideStatus, BigDecimal ridePrice, String rideDescription, LocalDateTime createdAt, User user, Vehicle vehicle, Location pickupLocation, Location dropoffLocation) {
        this.rideName = rideName;
        this.rideStatus = rideStatus;
        this.ridePrice = ridePrice;
        this.rideDescription = rideDescription;
        this.createdAt = createdAt;
        //this.location = location;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
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


    public Location getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public Location getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(Location dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
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
                " pickupLocation=" + pickupLocation +
                ", dropoffLocation=" + dropoffLocation +
                ", user=" + user +
                ", vehicle=" + vehicle +
                '}';
    }



}
