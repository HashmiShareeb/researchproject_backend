package com.example.researchproject.application.ports.in;
import com.example.researchproject.application.ports.dto.VehicleDTO;
import com.example.researchproject.application.ports.out.VehicleRepository;
import com.example.researchproject.domain.models.Vehicle;
import java.util.List;

public interface VehicleUseCase {


    //create vehicle
    Vehicle CreateVehicle(Vehicle vehicle);

    //get vehicle by id
    Vehicle GetVehicleById(String vehicleId);

    //get vehicles
    List<Vehicle> GetVehicles();

    //delete vehicle
    void DeleteVehicle(String vehicleId);

    //update vehicle
    Vehicle UpdateVehicle(Vehicle vehicle);

    //exists by license plate
    boolean existsByLicensePlate(String licensePlate);

    String getVehicleImage(String vehicleId);



}
