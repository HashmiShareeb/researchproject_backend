package com.example.researchproject.application.ports.services;


import com.example.researchproject.application.ports.in.RideUseCase;
import com.example.researchproject.application.ports.out.repositories.RideRepository;
import com.example.researchproject.domain.exceptions.RideNotFoundException;
import com.example.researchproject.domain.models.Ride;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


@Service
public class RiderService implements RideUseCase {
    @Autowired
    private RideRepository riderRepo;

    @Override
    public Ride CreateRide(Ride ride) {
        return riderRepo.save(ride);
    }

    @Override
    public Ride getRidesByDriverId(Long driverId) {
        return null;
    }

    @Override
    public List<Ride> GetRides() {
        return riderRepo.findAll();
    }

    @Override
    public Ride GetRideById(Long riderId) {
        return riderRepo.findById(riderId).orElseThrow(
                () -> new RideNotFoundException("Ride with id " + riderId + " not found or does not exist")
        );
    }

    @Override
    public void DeleteRide(Long rideId) {
        riderRepo.deleteById(rideId);
    }

    @Override
    public Ride UpdateRide(Ride ride) {
        return riderRepo.save(ride);
    }

    @Override
    public Ride GetRideDetails(Long rideId) {
        return riderRepo.findById(rideId).orElse(null);
    }


}
