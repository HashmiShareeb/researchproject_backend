package com.example.researchproject.application.ports.dto;

import com.example.researchproject.domain.models.Vehicle;
import com.example.researchproject.domain.models.enums.VehichleStatus;

public class VehicleDTO {
    private String vehicleId;
    private String manufacturer;
    private String model;
    private String licensePlate;
    private Integer batteryLevel;
    private VehichleStatus vehicleStatus;



    public VehicleDTO(Vehicle vehicle) {
        this.vehicleId = vehicle.getVehicleId();
        this.manufacturer = vehicle.getManufacturer();
        this.model = vehicle.getModel();
        this.licensePlate = vehicle.getLicensePlate();
        this.batteryLevel = vehicle.getBatteryLevel();
        this.vehicleStatus = vehicle.getVehicleStatus();
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

    public Integer getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(Integer batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public VehichleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehichleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }
}


