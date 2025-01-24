package com.example.researchproject.infrastructure.adapters.input;

import com.example.researchproject.application.ports.dto.RideDTO;
import com.example.researchproject.application.services.RideService;
import com.example.researchproject.application.services.UserService;
import com.example.researchproject.domain.models.Ride2;
import com.example.researchproject.domain.models.enums.RideStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rides")
public class RideControllerNew {

    @Autowired
    RideService rideService;

    @Autowired
    UserService userService;


    @GetMapping
    public ResponseEntity<List<RideDTO>> getRides() {
        List<RideDTO> rides = rideService.GetRides();
        return ResponseEntity.ok(rides);
    }

    @GetMapping("/{rideId}")
    public ResponseEntity<RideDTO> getRideById(@PathVariable String rideId) {
        Ride2 ride = rideService.GetRideById(rideId);
        return ResponseEntity.ok(new RideDTO(ride));
    }


    @GetMapping("/history/{userId}")
    public ResponseEntity<List<RideDTO>> getRideHistory(@PathVariable String userId) {
        List<Ride2> rides = rideService.GetRideHistory(userId);
        List<RideDTO> rideDTOS = rides.stream().map(RideDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok(rideDTOS);
    }


    @PutMapping("/end/{rideId}")
    public ResponseEntity<?> endRide(@PathVariable String rideId) {
        try {
            Ride2 ride = rideService.EndRide(rideId);
            return ResponseEntity.ok(ride);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred:" + e.getMessage());
        }
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

    @PostMapping("/request/{userId}/{vehicleId}")
    public ResponseEntity<Ride2> requestRide( @PathVariable String userId,@PathVariable String vehicleId, @RequestBody RideDTO rideDTO){

        Ride2 ride = rideService.RequestRide(rideDTO, userId, vehicleId);

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
