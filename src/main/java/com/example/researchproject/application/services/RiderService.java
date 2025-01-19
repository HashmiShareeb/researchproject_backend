package com.example.researchproject.application.services;


import com.example.researchproject.application.ports.in.RideUseCase;
import com.example.researchproject.application.ports.out.RideRepository;
import com.example.researchproject.application.ports.out.UserRepository;
import com.example.researchproject.domain.exceptions.RideNotFoundException;
import com.example.researchproject.domain.models.Ride;
import com.example.researchproject.domain.models.User;
import com.example.researchproject.domain.models.enums.RideStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


@Service
public class RiderService implements RideUseCase {

    private final RideRepository riderRepo;
    private final UserRepository userRepo;

    @Autowired
    public RiderService(RideRepository riderRepo, UserRepository userRepo) {
        this.riderRepo = riderRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Ride CreateRide(Ride ride) {
        if (ride.getUser() == null) {
            throw new UsernameNotFoundException("User cannot be null when creating a ride");
        }
        User user = userRepo.findByUsername(ride.getUser().getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + ride.getUser().getUsername()));

        ride.setUser(user); // Ensure ride has a user before saving
        ride.setVehicle(null); //vehicle even weg (voor testen)
        return riderRepo.save(ride);
    }

    @Override
    public Ride getRidesByDriverId(String driverId) {
        return null;
    }

    @Override
    public List<Ride> GetRides() {
        return riderRepo.findAll();
    }

    @Override
    public Ride GetRideById(String riderId) {
        return riderRepo.findById(riderId).orElseThrow(
                () -> new RideNotFoundException("Ride with id " + riderId + " not found or does not exist")
        );
    }


    @Override
    public void DeleteRide(String rideId) {
        riderRepo.deleteById(rideId);
    }

    @Override
    public Ride UpdateRide(Ride ride) {
        return riderRepo.save(ride);
    }

    @Override
    public Ride GetRideDetails(String rideId) {
        return riderRepo.findById(rideId).orElse(null);
    }

    @Override
    public List<Ride> findByUser(User user) {
        return riderRepo.findByUser(user);
    }


    /*@Override
    public Ride updateRideStatus(String rideId, RideStatus status) {
        // Fetch the ride by its ID
        Ride ride = riderRepo.findById(rideId).orElseThrow(() -> new RideNotFoundException("Ride with id " + rideId + " not found or does not exist"));

        // Set the new ride status
        ride.setRideStatus(status);

        // Save the updated ride
        return riderRepo.save(ride);
    }*/

    @Override
    public Ride updateRideStatus(String rideId, RideStatus status) {
        Optional<Ride> optionalRide = riderRepo.findById(rideId);

        if (optionalRide.isPresent()) {
            Ride ride = optionalRide.get();

            // Log the current status before updating
            System.out.println("Current Ride Status: " + ride.getRideStatus());

            // Check if the ride is in REQUESTED status before starting
            if ("REQUESTED".equals(ride.getRideStatus().name())) {
                ride.setRideStatus(status);  // update status
                System.out.println("Updated Ride Status: " + ride.getRideStatus()); // Log the updated status
                return riderRepo.save(ride);  // save and return the updated ride
            } else {
                throw new RideNotFoundException("Ride with id " + rideId + " is not in REQUESTED status.");
            }
        } else {
            throw new RideNotFoundException("Ride with id " + rideId + " not found.");
        }
    }

    public Ride startRide(String rideId) {
        // Find the ride by ID
        Ride ride = riderRepo.findById(rideId).orElse(null);

        if (ride != null) {
            ride.setVehicle(null); //vehicle even weg (voor testen)

            // Change the ride status to IN_PROGRESS
            ride.setRideStatus(RideStatus.IN_PROGRESS);
            riderRepo.save(ride);
        }

        return ride;
    }



    /*public Ride updateRideStatus(String rideId, RideStatus status) {
        // Ensure the rideId is correctly passed as String
        System.out.println("Looking for ride with ID: " + rideId);
        Ride ride = riderRepo.findById(rideId).orElseThrow(
                () -> new RideNotFoundException("Ride with id " + rideId + " not found or does not exist")
        );
        System.out.println("Found ride: " + ride.getRideName());  // Log the found ride

        ride.setRideStatus(status);

        return riderRepo.save(ride);
    }*/
    /*public Ride updateRideStatus(String rideId, RideStatus status) {
        Ride ride = riderRepo.findById(rideId).orElseThrow(
                () -> new RideNotFoundException("Ride with id " + rideId + " not found or does not exist")
        );
        ride.setRideStatus(status);
        return riderRepo.save(ride);
    }*/

    @Override
    public List<Ride> getRideHistory(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return riderRepo.findByUser(user);
    }


}
