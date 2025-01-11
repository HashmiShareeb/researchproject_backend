package com.example.researchproject.infrastructure.adapters.input.out;

import com.example.researchproject.application.services.OwnerService;
import com.example.researchproject.application.services.VehicleService;
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
        return ResponseEntity.ok(vehicle);
    }

    //create vehicle
    @PostMapping("/api/vehicles")
    public ResponseEntity<Vehicle> CreateVehicle(@RequestBody Vehicle vehicle) {
        Vehicle createdVehicle = vehicleService.CreateVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicle);
    }

    //assign vehicle to an owner once vehicle exists
    @PutMapping("/api/vehicles/{vehicleId}/assign-owner/{ownerId}")
    public ResponseEntity<?> assignVehicleToOwner(@PathVariable String vehicleId, @PathVariable String ownerId) {
        //get the vehicle id
        Vehicle vehicle = vehicleService.GetVehicleById(vehicleId);
        if (vehicle == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not found");
        }
        Owner owner = ownerService.findById(ownerId);
        if (owner == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Owner not found");
        }
        vehicle.setOwner(owner);
        //vehicleService.CreateVehicle(vehicle);
        return ResponseEntity.ok(vehicle);
    }

    //delete vehicle
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/api/vehicles/{vehicleId}")
    public void deleteVehicle(@PathVariable String vehicleId) {
        vehicleService.DeleteVehicle(vehicleId);
    }








}


