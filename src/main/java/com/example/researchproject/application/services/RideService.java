package com.example.researchproject.application.services;

import com.example.researchproject.application.ports.dto.RideDTO;
import com.example.researchproject.application.ports.in.RideUseCase;
import com.example.researchproject.application.ports.out.RideRepository;
import com.example.researchproject.application.ports.out.UserRepository;
import com.example.researchproject.application.ports.out.VehicleRepository;
import com.example.researchproject.domain.exceptions.RideAlreadyStartedException;
import com.example.researchproject.domain.exceptions.RideNotFoundException;
import com.example.researchproject.domain.exceptions.VehicleNotFoundException;
import com.example.researchproject.domain.models.Ride;
import com.example.researchproject.domain.models.User;
import com.example.researchproject.domain.models.Vehicle;
import com.example.researchproject.domain.models.enums.RideStatus;
import com.example.researchproject.domain.models.enums.VehichleStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RideService implements RideUseCase {

    private final RideRepository rideRepo;
    private final UserRepository userRepo;
    private final VehicleRepository vehicleRepo;

    public RideService(RideRepository rideRepo, UserRepository userRepo, VehicleRepository vehicleRepo) {
        this.rideRepo = rideRepo;
        this.userRepo = userRepo;
        this.vehicleRepo = vehicleRepo;
    }

    @Override
    public Ride CreateRide(Ride ride) {

        return rideRepo.save(ride);
    }

    @Override
    public Ride GetRideById(String rideId) {
        return rideRepo.findById(rideId).orElseThrow(() -> new RideNotFoundException("Ride with ID " + rideId + " not found."));
    }

    @Override
    public List<RideDTO> GetRides() {
        return rideRepo.findAll().stream().map(RideDTO::new).toList();

    }

    @Override
    public void DeleteRide(String rideId) {
        rideRepo.deleteById(rideId);
    }

    @Override
    public Ride UpdateRide(Ride ride) {
        if (!rideRepo.existsById(ride.getRideId())) {
            throw new RideNotFoundException("Ride with ID " + ride.getRideId() + " not found.");
        }
        return rideRepo.save(ride);
    }


    @Override
    public Ride RequestRide(RideDTO rideDTO, String userId, String vehicleId) { //nulable = met of zonder vehicleId
        // Ensure user exists
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + rideDTO.getUser().getUserId()));

        Vehicle vehicle = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found with ID: " + vehicleId));

        if (vehicle.getVehicleStatus() != VehichleStatus.AVAILABLE) {
            throw new IllegalStateException("Vehicle is not available");
        }

        //enkel vehicle status zoekt naar AVAILABLE
        Vehicle v = vehicleRepo.findFirstByVehicleStatus(VehichleStatus.AVAILABLE).orElseThrow(
                () -> new VehicleNotFoundException("No available vehicles found.")

        );


        // Create a new Ride object using DTO data
        Ride ride = new Ride();
        ride.setRideStatus(RideStatus.REQUESTED);
        ride.setUser(user);
        ride.setRideId(rideDTO.getRideId());
        ride.setRideName(rideDTO.getRideName());
        ride.setRidePrice(rideDTO.getRidePrice());
        ride.setRideDescription(rideDTO.getRideDescription());
        ride.setCreatedAt(LocalDateTime.now());
        ride.setLocation(rideDTO.getLocation());
        ride.setVehicle(vehicle); // 🚗 Assign the vehicle

        // Update vehicle status naar "IN_USE" wanneer de ride is aangemaakt
        vehicle.setVehicleStatus(VehichleStatus.IN_USE);
        vehicleRepo.save(vehicle);

        // Save to database
        ride =  rideRepo.save(ride);

        return ride;
    }

    @Override
    public Ride StartRide(String rideId) {
        Ride ride = rideRepo.findById(rideId)
                .orElseThrow(() -> new RideNotFoundException("Ride with ID " + rideId + " not found."));

        // Check if the ride can be started
        if (ride.getRideStatus() == RideStatus.IN_PROGRESS) {
            throw new RideAlreadyStartedException("Ride is already in progress.");
        } else if (ride.getRideStatus() == RideStatus.COMPLETED) {
            throw new IllegalStateException("Ride has already been completed.");
        }

        //json response niet nodig want hier is het all aangpast
        ride.setRideStatus(RideStatus.IN_PROGRESS);

        return rideRepo.save(ride);
    }

    @Override
    public Ride EndRide(String rideId) {
        Ride ride = rideRepo.findById(rideId)
                .orElseThrow(() -> new RideNotFoundException("Ride with ID " + rideId + " not found."));


        if (ride.getRideStatus() == RideStatus.COMPLETED) {
            throw new IllegalStateException("Ride has already been completed.");
        }

        //json response niet nodig want hier is het all aangpast!
        ride.setRideStatus(RideStatus.COMPLETED);

        //omdat de rit beëindigd is, is de vehicle terug AVAILABLE
        Vehicle vehicle = ride.getVehicle();
        if (vehicle != null) {
            vehicle.setVehicleStatus(VehichleStatus.AVAILABLE);
            vehicleRepo.save(vehicle);
        }

        return rideRepo.save(ride);
    }

    @Override
    public List<Ride> GetRideHistory(String userId) {
        return rideRepo.findByUser_UserIdAndRideStatus(userId, RideStatus.COMPLETED);
    }


}
