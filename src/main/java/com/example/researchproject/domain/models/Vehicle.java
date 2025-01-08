package com.example.researchproject.domain.models;
import com.example.researchproject.domain.models.enums.VehichleStatus;


public class Vehicle {
    private Long vehicleId;
    private String brand;
    private String model;
    private String licensePlate;
    private Integer year;
    private Driver driver;
    private VehichleStatus vehicleStatus;


    public Vehicle(String brand, String model, String licensePlate, Integer year, Driver driver, VehichleStatus vehicleStatus) {
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.year = year;
        this.driver = driver;
        this.vehicleStatus = vehicleStatus;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }


    public VehichleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehichleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }
    protected Vehicle() {
    }
}
