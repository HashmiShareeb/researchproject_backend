package com.example.researchproject.application.ports.services;


import com.example.researchproject.application.ports.in.RideUseCase;
import com.example.researchproject.application.ports.out.repositories.RideRepository;
import com.example.researchproject.domain.exceptions.RideNotFoundException;
import com.example.researchproject.domain.models.Rides;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


@Service
public class RiderService implements RideUseCase {
    @Autowired
    private RideRepository riderRepo;

    @Override
    public Rides CreateRide(Rides ride) {
        return riderRepo.save(ride);
    }

    @Override
    public Rides getRidesByDriverId(Long driverId) {
        return null;
    }

    @Override
    public List<Rides> GetRides() {
        return riderRepo.findAll();
    }

    @Override
    public Rides GetRideById(Long riderId) {
        return riderRepo.findById(riderId).orElseThrow(
                () -> new RideNotFoundException("Ride with id " + riderId + " not found or does not exist")
        );
    }

    @Override
    public void DeleteRide(Long rideId) {
        riderRepo.deleteById(rideId);
    }

    @Override
    public Rides UpdateRide(Rides ride) {
        return riderRepo.save(ride);
    }

    @Override
    public Rides GetRideDetails(Long rideId) {
        return riderRepo.findById(rideId).orElse(null);
    }


}
