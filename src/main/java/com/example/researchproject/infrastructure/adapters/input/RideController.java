package com.example.researchproject.infrastructure.adapters.input;

import com.example.researchproject.domain.models.Ride;
import com.example.researchproject.application.services.RiderService;
import com.example.researchproject.domain.models.enums.RideStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    @Autowired
    RiderService riderService;

    // Get all rides
    @GetMapping
    public List<Ride> findAll() {
        return riderService.GetRides();
    }

    // Get a ride by its ID
    @GetMapping("/{rideId}")
    public ResponseEntity<Ride> getRideById(@PathVariable Long rideId) {
        Ride ride = riderService.GetRideById(rideId);
        return ResponseEntity.ok(ride);
    }


    // Add a new ride
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addRide(@RequestBody Ride ride) {

        riderService.CreateRide(ride);
    }

    // Delete a ride by its ID
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{rideId}")
    public void deleteRide(@PathVariable Long rideId) {
        riderService.DeleteRide(rideId);
    }

    //by able to cancel a ride
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{rideId}/cancel")
    public void cancelRide(@PathVariable Long rideId) {
        Ride ride = riderService.GetRideById(rideId);
        ride.setRideStatus(RideStatus.valueOf("Cancelled"));
        riderService.UpdateRide(ride);
    }



}


