package com.example.researchproject.application.ports.services;

import com.example.researchproject.application.ports.out.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.researchproject.domain.models.Vehicle;

import java.util.List;

@Service
public class VehicleService {

   private final VehicleRepository vehicleRepo;

   public VehicleService(VehicleRepository vehicleRepo) {
       this.vehicleRepo = vehicleRepo;
   }

    public List<Vehicle> GetVehicles() {
        return vehicleRepo.findAll();
    }

    public Vehicle CreateVehicle(Vehicle vehicle) {
        return vehicleRepo.save(vehicle);
    }

    public Vehicle GetVehicleById(String vehicleId) {
        return vehicleRepo.findById(vehicleId).orElse(null);
    }

    public void DeleteVehicle(String vehicleId) {
        vehicleRepo.deleteById(vehicleId);
    }





}
