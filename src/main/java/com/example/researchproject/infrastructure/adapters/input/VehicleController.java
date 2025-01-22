package com.example.researchproject.infrastructure.adapters.input;

import com.example.researchproject.application.ports.dto.VehicleDTO;
import com.example.researchproject.application.services.VehicleService;
import com.example.researchproject.domain.exceptions.VehicleNotFoundException;
import com.example.researchproject.domain.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getVehicle() {
        List<Vehicle> vehicles = vehicleService.GetVehicles();
        return ResponseEntity.ok(vehicles);
    }

    //get vehicle by id
    @GetMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> getVehiclesById(@PathVariable String vehicleId) {
        Vehicle vehicle = vehicleService.GetVehicleById(vehicleId);
        if (vehicle == null) {
            throw new VehicleNotFoundException("Vehicle with id " + vehicleId + " not found or does not exist");
        }
        return ResponseEntity.ok(vehicle);
    }

    //get vehicles by status

    /*@GetMapping("/status/{status}")
    public ResponseEntity<List<VehicleDTO>> getVehiclesByStatus(@PathVariable String status) {
        if (status == null) {
            throw new VehicleNotFoundException("Vehicle with status " + status + " not found or does not exist");
        }
        List<VehicleDTO> vehicles = vehicleService.GetVehiclesByStatus(status);
        return ResponseEntity.ok(vehicles);


    }*/

    //create vehicle
    @PostMapping
    public ResponseEntity<Vehicle> CreateVehicle(@RequestBody Vehicle vehicle) {

        Vehicle createdVehicle = vehicleService.CreateVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicle);
    }

    //update current vehicle
    @PutMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> updateVehicle(
            @PathVariable String vehicleId,
            @RequestBody Vehicle updatedVehicle) {
        // Fetch the existing vehicle
        Vehicle existingVehicle = vehicleService.GetVehicleById(vehicleId);
        if (existingVehicle == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Update details
        updatedVehicle.setVehicleId(vehicleId); // Ensure the ID remains the same
        Vehicle savedVehicle = vehicleService.UpdateVehicle(updatedVehicle);

        return ResponseEntity.ok(savedVehicle);
    }

    //delete vehicle
    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<?> deleteVehicle(@PathVariable String vehicleId) {
        vehicleService.DeleteVehicle(vehicleId);
        return ResponseEntity.ok("Vehicle with id " + vehicleId + " deleted successfully");
    }

}
