package com.example.researchproject.application.services;

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
    private final OwnerRepository ownerRepository;

    private final VehicleUseCase vehicleUseCase;

    @Autowired
    @Lazy
    public VehicleService(VehicleRepository vehicleRepository, OwnerRepository ownerRepo, VehicleUseCase vehicleUseCase) {
        this.vehicleRepository = vehicleRepository;
        this.ownerRepository = ownerRepo;
        this.vehicleUseCase = vehicleUseCase;
    }

    @Override
    public List<Vehicle> GetVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle CreateVehicle(Vehicle vehicle) {
        // Check if the vehicle has an owner. If it doesn't, no issue, just save it without the owner.
        if (vehicle.getOwner() != null && vehicle.getOwner().getOwnerId() != null) {
            // Validate the owner if necessary
            Owner owner = ownerRepository.findById(vehicle.getOwner().getOwnerId())
                    .orElseThrow(() -> new VehicleNotFoundException("Vehicle with id " + vehicle.getOwner().getOwnerId() + " not found or does not exist"));
            vehicle.setOwner(owner); // Assign the owner properly
        }

        return vehicleRepository.save(vehicle); // Save the vehicle (with or without an owner)
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
