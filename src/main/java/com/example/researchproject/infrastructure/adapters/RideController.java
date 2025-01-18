package com.example.researchproject.infrastructure.adapters;

import com.example.researchproject.application.ports.out.UserRepository;
import com.example.researchproject.application.services.UserService;
import com.example.researchproject.domain.models.Ride;
import com.example.researchproject.application.services.RiderService;
import com.example.researchproject.domain.models.User;
import com.example.researchproject.domain.models.enums.RideStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    @Autowired
    RiderService riderService;

    @Autowired
    UserRepository userRepo;


    // Get all rides
    @GetMapping
    public List<Ride> findAll() {
        return riderService.GetRides();
    }

    // Get a ride by its ID
    @GetMapping("/{rideId}")
    public ResponseEntity<Ride> getRideById(@PathVariable String rideId) {
        Ride ride = riderService.GetRideById(rideId);
        return ResponseEntity.ok(ride);
    }

    @PostMapping("/request")
    public ResponseEntity<Ride> requestRide(@RequestBody Map<String, Object> requestData) {
        String username = (String) requestData.get("username");

        if (username == null) {
            return ResponseEntity.badRequest().body(null); // Handle missing username properly
        }

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        Ride ride = new Ride();
        ride.setUser(user);
        ride.setRideName((String) requestData.get("rideName"));
        ride.setRideStatus(RideStatus.valueOf((String) requestData.get("rideStatus")));
        ride.setRidePrice(new BigDecimal(requestData.get("ridePrice").toString()));

        Ride createdRide = riderService.CreateRide(ride);
        return ResponseEntity.ok(createdRide);
    }



    //get ride history
    @GetMapping("/history/{username}")
    public ResponseEntity<List<Ride>> getRideHistory(@PathVariable String username) {
        return ResponseEntity.ok(riderService.getRideHistory(username));
    }

    //update ride status
    /*@PutMapping("/{rideId}/status")
    public ResponseEntity<Ride> updateRideStatus(@PathVariable String rideId, @RequestBody RideStatus status) {
        Ride ride = riderService.updateRideStatus(rideId, status);
        return ResponseEntity.ok(ride);
    } */

    @PutMapping("/{rideId}/status")
    public ResponseEntity<Ride> updateRideStatus(@PathVariable String rideId, @RequestBody Map<String, Object> updates) {
        String status = (String) updates.get("status");
        Ride updatedRide = riderService.updateRideStatus(rideId, RideStatus.valueOf(status));
        return ResponseEntity.ok(updatedRide);
    }

    // Add a new ride
    /*@ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addRide(@RequestBody Ride ride) {

        riderService.CreateRide(ride);
    }*/

    // Delete a ride by its ID
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{rideId}")
    public void deleteRide(@PathVariable String rideId) {
        riderService.DeleteRide(rideId);
    }

    //by able to cancel a ride
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{rideId}/cancel")
    public void cancelRide(@PathVariable String rideId) {
        Ride ride = riderService.GetRideById(rideId);
        ride.setRideStatus(RideStatus.valueOf("Cancelled"));
        riderService.UpdateRide(ride);
    }



}


