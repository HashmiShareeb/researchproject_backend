package com.example.researchproject.application.services;

import com.example.researchproject.application.ports.dto.VehicleDTO;
import com.example.researchproject.application.ports.in.VehicleUseCase;
import com.example.researchproject.application.ports.out.OwnerRepository;
import com.example.researchproject.application.ports.out.VehicleRepository;
import com.example.researchproject.domain.exceptions.VehicleNotFoundException;
import com.example.researchproject.domain.models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.researchproject.domain.models.Vehicle;

import java.util.List;

@Service
public class VehicleService implements VehicleUseCase {

    private final VehicleRepository vehicleRepository;
    private final VehicleUseCase vehicleUseCase;

    @Autowired
    @Lazy
    public VehicleService(VehicleRepository vehicleRepository, VehicleUseCase vehicleUseCase) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleUseCase = vehicleUseCase;
    }

    @Override
    public List<Vehicle> GetVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle CreateVehicle(Vehicle vehicle) {

        return vehicleRepository.save(vehicle);
    }


    @Override
    public Vehicle GetVehicleById(String vehicleId) {
        return vehicleRepository.findById(vehicleId).orElse(null);
    }
    @Override
    public void DeleteVehicle(String vehicleId) {
        vehicleRepository.deleteById(vehicleId);
    }

    @Override
    public Vehicle UpdateVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public boolean existsByLicensePlate(String licensePlate) {
        return vehicleRepository.existsByLicensePlate(licensePlate);
    }

   @Override
    public String getVehicleImage(String vehicleId) {
        return vehicleUseCase.getVehicleImage(vehicleId);
    }




}
