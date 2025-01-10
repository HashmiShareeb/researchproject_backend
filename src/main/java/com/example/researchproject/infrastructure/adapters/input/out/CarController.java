package com.example.researchproject.infrastructure.adapters.input.out;

import com.example.researchproject.application.ports.services.OwnerService;
import com.example.researchproject.application.ports.services.VehicleService;
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
    VehicleService vehicleService;

    //OWNERS
    @GetMapping("/api/owners")
    public ResponseEntity<List<Owner>> getAllOwners() {
        List<Owner> owners = ownerService.getOwners();
        return ResponseEntity.ok(owners);
    }

    @GetMapping("/api/owners/{ownerId}")
    public ResponseEntity<Owner> getOwner(@PathVariable String ownerId) {
        Owner owner = ownerService.findById(ownerId);
        return ResponseEntity.ok(owner);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/owners")
    public ResponseEntity<?> createOwner(@RequestBody Owner owner) {
        try {
            Owner createdOwner = ownerService.CreateOwner(owner);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOwner);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
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

    // @GetMapping("/api/owners/{ownerId}/vehicles")
    // public ResponseEntity<List<Vehicle>> getVehicle(@PathVariable String ownerId) {
    //     Owner owner = ownerService.findById(ownerId);
    //     return ResponseEntity.ok(owner.getVehicle());
    // }

    // @PostMapping("/api/owners/{ownerId}/vehicles")
    // public ResponseEntity<?> createVehicle(@PathVariable String ownerId, @RequestBody Vehicle vehicle) {
    //     Owner owner = ownerService.findById(ownerId);
    //     owner.getVehicle().add(vehicle);
    //     ownerService.CreateOwner(owner);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
    // }


    //VEHICLES
    @GetMapping("/api/vehicles")
    public ResponseEntity<List<Vehicle>> getVehicle() {
        List<Vehicle> vehicles = vehicleService.GetVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/api/vehicles/{vehicleId}")
    public ResponseEntity<Vehicle> getVehiclesById(@PathVariable String vehicleId) {
        Vehicle vehicle = vehicleService.GetVehicleById(vehicleId);
        return ResponseEntity.ok(vehicle);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/vehicles")
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        Vehicle createdVehicle = vehicleService.CreateVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicle);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/api/vehicles/{vehicleId}")
    public void deleteVehicle(@PathVariable String vehicleId) {
        vehicleService.DeleteVehicle(vehicleId);
    }








}
