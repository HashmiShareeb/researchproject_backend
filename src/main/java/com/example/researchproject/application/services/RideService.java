package com.example.researchproject.application.services;

import com.example.researchproject.application.ports.dto.RideDTO;
import com.example.researchproject.application.ports.in.RideUseCase;
import com.example.researchproject.application.ports.out.RideRepository;
import com.example.researchproject.application.ports.out.UserRepository;
import com.example.researchproject.application.ports.out.VehicleRepository;
import com.example.researchproject.domain.exceptions.RideAlreadyStartedException;
import com.example.researchproject.domain.exceptions.RideNotFoundException;
import com.example.researchproject.domain.exceptions.VehicleNotFoundException;
import com.example.researchproject.domain.models.Ride.Ride;
import com.example.researchproject.domain.models.Ride.RideStatus;
import com.example.researchproject.domain.models.User.User;
import com.example.researchproject.domain.models.Vehicle.VehichleStatus;
import com.example.researchproject.domain.models.Vehicle.Vehicle;

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
        return rideRepo.findById(rideId)
                .orElseThrow(() -> new RideNotFoundException("Ride with ID " + rideId + " not found."));
    }

    @Override
    public List<RideDTO> GetRides() {
        return rideRepo.findAll()
                .stream()
                .map(RideDTO::new)
                .toList();
    }

    @Override
    public void deleteRide(String rideId) {
        if (!rideRepo.existsById(rideId)) {
            throw new RideNotFoundException("Ride with ID " + rideId + " not found.");
        }
        rideRepo.deleteById(rideId);
    }

    @Override
    public Ride updateRide(Ride ride) {
        if (!rideRepo.existsById(ride.getRideId())) {
            throw new RideNotFoundException("Ride with ID " + ride.getRideId() + " not found.");
        }
        return rideRepo.save(ride);
    }

    @Override
    public Ride RequestRide(RideDTO rideDTO, String userId, String vehicleId) {
        // 1. Fetch User --> ensure user exists
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));

        // 2. Fetch Vehicle or find first AVAILABLE
        Vehicle vehicle = (vehicleId != null)
                ? vehicleRepo.findById(vehicleId).orElseThrow(() -> new VehicleNotFoundException("Vehicle not found: " + vehicleId))
                : vehicleRepo.findFirstByVehicleStatus(VehichleStatus.AVAILABLE)
                .orElseThrow(() -> new VehicleNotFoundException("No available vehicles found."));

        if (vehicle.getVehicleStatus() != VehichleStatus.AVAILABLE) {
            throw new IllegalStateException("Selected vehicle is not available.");
        }

        // 3. Create Ride
        Ride ride = new Ride();
        ride.setRideName(rideDTO.getRideName());
        ride.setRidePrice(rideDTO.getRidePrice());
        ride.setRideDescription(rideDTO.getRideDescription());
        ride.setCreatedAt(LocalDateTime.now());
        ride.setRideStatus(RideStatus.REQUESTED);
        ride.setPickupLocation(rideDTO.getPickupLocation());
        ride.setDropoffLocation(rideDTO.getDropoffLocation());
        ride.setUser(user);
        ride.setVehicle(vehicle);

        // 4. Update vehicle status to IN_USE
        vehicle.setVehicleStatus(VehichleStatus.IN_USE);
        vehicleRepo.save(vehicle);

        return rideRepo.save(ride);
    }

    @Override
    public Ride startRide(String rideId) {
        Ride ride = rideRepo.findById(rideId)
                .orElseThrow(() -> new RideNotFoundException("Ride with ID " + rideId + " not found."));

        if (ride.getRideStatus() == RideStatus.IN_PROGRESS) {
            throw new RideAlreadyStartedException("Ride is already in progress.");
        } else if (ride.getRideStatus() == RideStatus.COMPLETED) {
            throw new IllegalStateException("Ride has already been completed.");
        }

        ride.setRideStatus(RideStatus.IN_PROGRESS);
        return rideRepo.save(ride);
    }

    @Override
    public Ride endRide(String rideId) {
        Ride ride = rideRepo.findById(rideId)
                .orElseThrow(() -> new RideNotFoundException("Ride with ID " + rideId + " not found."));

        if (ride.getRideStatus() == RideStatus.COMPLETED) {
            throw new IllegalStateException("Ride is already completed.");
        }

        // Update ride status
        ride.setRideStatus(RideStatus.COMPLETED);

        // Free up the vehicle
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

    @Override
    public List<Ride> GetRidesByUserId(String userId) {
        return rideRepo.findByUser_UserIdAndRideStatus(userId, RideStatus.REQUESTED);
    }


}
