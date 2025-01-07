package com.example.researchproject.application.ports.in;

import com.example.researchproject.domain.models.Rides;
import com.example.researchproject.domain.services.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class RobottaxiController {

    @Autowired
    RiderService riderService;

    // Get all rides
    @GetMapping("/rides")
    public List<Rides> findAll() {
        return riderService.GetRides();
    }
    // Get a ride by its ID
    @GetMapping("/rides/{rideId}")
    public ResponseEntity<Rides> findById(@PathVariable Long rideId) {
        Rides ride = riderService.GetRideById(rideId);
        if (ride != null) {
            return ResponseEntity.ok(ride);
        }
        return ResponseEntity.notFound().build();
    }

    // Add a new ride
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addRide(@RequestBody Rides ride) {
        riderService.AddRide(ride);
    }

    // Delete a ride by its ID
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/rides/{rideId}")
    public void deleteRide(@PathVariable Long rideId) {
        riderService.DeleteRide(rideId);
    }
}
