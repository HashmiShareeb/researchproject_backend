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



    protected VehicleDTO(Vehicle vehicle) {
    }




    public VehicleDTO(String vehicleId, String manufacturer, String model, String licensePlate, Integer batteryLevel, VehichleStatus vehicleStatus) {
        this.vehicleId = vehicleId;
        this.manufacturer = manufacturer;
        this.model = model;
        this.licensePlate = licensePlate;
        this.batteryLevel = batteryLevel;
        this.vehicleStatus = vehicleStatus;
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

    @Override
    public String toString() {
        return "VehicleDTO{" +
                "vehicleId='" + vehicleId + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", batteryLevel=" + batteryLevel +
                ", vehicleStatus=" + vehicleStatus +
                '}';
    }
}


