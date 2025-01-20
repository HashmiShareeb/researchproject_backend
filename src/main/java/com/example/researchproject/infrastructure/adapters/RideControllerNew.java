package com.example.researchproject.infrastructure.adapters;

import com.example.researchproject.application.ports.dto.RideDTO;
import com.example.researchproject.application.services.RideService;
import com.example.researchproject.application.services.RiderService;
import com.example.researchproject.application.services.UserService;
import com.example.researchproject.domain.models.Ride;
import com.example.researchproject.domain.models.Ride2;
import com.example.researchproject.domain.models.User;
import com.example.researchproject.domain.models.enums.RideStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/rides")
public class RideControllerNew {

    @Autowired
    RideService rideService;

    @Autowired
    UserService userService;


    @GetMapping
    public ResponseEntity<?> getRides() {
        return ResponseEntity.ok(rideService.GetRides());
    }




    /*@PutMapping("/start/{rideId}")
    public ResponseEntity<?> startRide(@PathVariable String rideId) {
        Ride2 ride = riderService.startRide(rideId);
        if (ride != null) {
            return ResponseEntity.ok("Ride started successfully.");
        }

        //ensure ride is in the requested state
        if (ride.getRideStatus() != RideStatus.REQUESTED) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ride is not in the requested state.");
        }

        ride.setRideStatus(RideStatus.IN_PROGRESS);
        riderService.UpdateRide(ride);

        return ResponseEntity.ok("Ride started successfully.");

    }*/

    @PutMapping("/start/{rideId}")
    public ResponseEntity<RideDTO> startRide(@PathVariable String rideId) {
        Ride2 ride = rideService.StartRide(rideId);
        return ResponseEntity.ok(new RideDTO(ride));

    }

    @PostMapping("/request")
    public ResponseEntity<Ride2> requestRide(@RequestBody RideDTO rideDTO) {
        Ride2 ride = rideService.RequestRide(rideDTO);


        if (ride.getRideStatus() == RideStatus.REQUESTED || ride.getRideStatus() == RideStatus.IN_PROGRESS) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ride already requested or in progress");
        }


        return ResponseEntity.ok(ride);

    }


    @DeleteMapping("/{rideId}")
    public ResponseEntity<?> deleteRide(@PathVariable String rideId) {
        rideService.DeleteRide(rideId);
        return ResponseEntity.ok("Ride deleted successfully.");
    }



}
