package com.example.researchproject.domain.models;
import com.example.researchproject.domain.models.enums.VehichleStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

@Entity
@Table(name = "vehicle")
//@JsonInclude(JsonInclude.Include.ALWAYS) //alleen voor niet lege waarden
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "vehicle_id", nullable = false, unique = true)
    private String vehicleId;
    @Column
    private String manufacturer;
    @Column
    private String model;
    @Column(name = "license_plate", unique = true, nullable = false)
    private String licensePlate;
    @Column
    private Integer year;
    @Column(nullable = true, name = "battery_level")
    private Integer batteryLevel;
    @Column(name = "vehicle_image", nullable = true)
    private String vehicleImage;

    //foreign key to owner
    @ManyToOne //many vehicles to one owner --> child
    @JoinColumn(name = "owner_id", nullable = true) // Allow owner to be null
    @JsonBackReference // Prevent JSON infinite looping
    private Owner owner;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_status", nullable = false)
    private VehichleStatus vehicleStatus;

    protected Vehicle() {
    }

    public Vehicle(String manufacturer, String model, String licensePlate, Integer year, VehichleStatus vehicleStatus,  Integer batteryLevel, String vehicleImage, Owner owner) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.licensePlate = licensePlate;
        this.year = year;
        this.vehicleStatus = vehicleStatus;
        this.batteryLevel = batteryLevel;
        this.vehicleImage = vehicleImage;
        this.owner = owner;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }


    public VehichleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehichleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }


    public Integer getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(Integer batteryLevel) {
        this.batteryLevel = batteryLevel;
    }


    public String getVehicleImage() {
        return vehicleImage;
    }

    public void setVehicleImage(String vehicleImage) {
        this.vehicleImage = vehicleImage;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", year=" + year +
                ", batteryLevel=" + batteryLevel +
                ", vehicleStatus=" + vehicleStatus +
                ", owner=" + (owner != null ? owner.getOwnerId() : null) +
                '}';
    }


}
