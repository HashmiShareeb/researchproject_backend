package com.example.researchproject.domain.models.Vehicle;
import com.example.researchproject.domain.exceptions.BatteryLevelException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

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
    @Min(0) @Max(100) // Battery level 0 and 100 check
    private Integer batteryLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_status", nullable = false)
    private VehichleStatus vehicleStatus;

    protected Vehicle() {

    }

    public Vehicle(String manufacturer, String model, String licensePlate, Integer year, VehichleStatus vehicleStatus,  Integer batteryLevel, String vehicleImage) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.licensePlate = licensePlate;
        this.year = year;
        this.vehicleStatus = vehicleStatus;
        this.batteryLevel = batteryLevel;

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

    public void updateBatteryLevel(int newBatteryLevel) {
        if(newBatteryLevel < 0 || newBatteryLevel > 100) {
            throw new BatteryLevelException("Invalid battery level " + newBatteryLevel + ". Battery level must be between 0 and 100.");
        }
        this.batteryLevel = newBatteryLevel;
    }
    public Integer getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(Integer batteryLevel) {
        this.batteryLevel = batteryLevel;
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
                '}';
    }


}
