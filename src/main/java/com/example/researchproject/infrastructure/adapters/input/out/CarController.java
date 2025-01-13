package com.example.researchproject.infrastructure.adapters.input.out;

import com.example.researchproject.application.services.OwnerService;
import com.example.researchproject.application.services.VehicleService;
import com.example.researchproject.domain.exceptions.VehicleNotFoundException;
import com.example.researchproject.domain.models.Owner;
import com.example.researchproject.domain.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    OwnerService ownerService;

    @Autowired
    VehicleService vehicleService;



    //OWNERS

    @GetMapping("/api/owners")
    public ResponseEntity<List<Owner>> getAllOwners() {
        List<Owner> owners = ownerService.getOwners();
        owners.forEach(owner -> {
            // Remove vehicles if the list is empty
            if (owner.getVehicle().isEmpty()) {
                owner.setVehicle(null);
            }
        });
        return ResponseEntity.ok(owners);
    }


    @GetMapping("/api/owners/{ownerId}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable String ownerId) {
        Owner owner = ownerService.findById(ownerId);
        return ResponseEntity.ok(owner);
    }

    @PostMapping("/api/owners")
    public ResponseEntity<?> createOwner(@RequestBody Owner owner) {
        try {
            Owner createdOwner = ownerService.CreateOwner(owner);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOwner);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @DeleteMapping("/api/owners/{ownerId}")
    public void deleteOwner(@PathVariable String ownerId) {
        ownerService.DeleteOwner(ownerId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/api/owners/{ownerId}")
    public ResponseEntity<?> updateOwner(@PathVariable String ownerId, @RequestBody Owner owner) {
        Owner updatedOwner = ownerService.CreateOwner(owner);
        return ResponseEntity.ok(updatedOwner);
    }

    @GetMapping("/api/owners/{ownerId}/vehicles")
    public ResponseEntity<List<Vehicle>> getOwnerVehicles(@PathVariable String ownerId) {
        Owner owner = ownerService.findById(ownerId);
        return ResponseEntity.ok(owner.getVehicle());
    }


    //VEHICLES
    @GetMapping("/api/vehicles")
    public ResponseEntity<List<Vehicle>> getVehicle() {
        List<Vehicle> vehicles = vehicleService.GetVehicles();
        return ResponseEntity.ok(vehicles);
    }

    //get vehicle by id
    @GetMapping("/api/vehicles/{vehicleId}")
    public ResponseEntity<Vehicle> getVehiclesById(@PathVariable String vehicleId) {
        Vehicle vehicle = vehicleService.GetVehicleById(vehicleId);
        if (vehicle == null) {
          throw new VehicleNotFoundException("Vehicle with id " + vehicleId + " not found or does not exist");
        }
        return ResponseEntity.ok(vehicle);
    }

    //create vehicle
    @PostMapping("/api/vehicles")
    public ResponseEntity<Vehicle> CreateVehicle(@RequestBody Vehicle vehicle) {

        Vehicle createdVehicle = vehicleService.CreateVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicle);
    }

    //update current vehicle
    @PutMapping("/api/vehicles/{vehicleId}")
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


    //assign vehicle to an owner once vehicle exists
    @PutMapping("/api/vehicles/{vehicleId}/assign-owner")
    public ResponseEntity<?> assignVehicleToOwner(
            @PathVariable String vehicleId,
            @RequestParam String ownerId) {

        // Fetch the vehicle by ID
        Vehicle vehicle = vehicleService.GetVehicleById(vehicleId);
        if (vehicle == null) {
            throw new VehicleNotFoundException("Vehicle with id " + vehicleId + " not found or does not exist");
        }

        // Fetch the owner by ID
        Owner owner = ownerService.findById(ownerId);
        if (owner == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Owner not found");
        }

        // Check if the vehicle is already assigned to an owner
        if (vehicle.getOwner() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Vehicle already assigned to another owner");
        }

        // Assign the owner to the vehicle
        vehicle.setOwner(owner);

        // Persist the changes
        vehicleService.UpdateVehicle(vehicle);

        return ResponseEntity.ok("Vehicle successfully assigned to owner");
    }


    //delete vehicle
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/api/vehicles/{vehicleId}")
    public void deleteVehicle(@PathVariable String vehicleId) {
        vehicleService.DeleteVehicle(vehicleId);
    }








}


