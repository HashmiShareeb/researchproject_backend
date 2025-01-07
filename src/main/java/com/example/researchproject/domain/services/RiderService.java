package com.example.researchproject.domain.services;


import com.example.researchproject.domain.repositories.RideRepository;
import com.example.researchproject.domain.models.Rides;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


@Service
public class RiderService {
    @Autowired
    private RideRepository riderRepo;

    public List<Rides> GetRides() {
        return riderRepo.findAll();
    }

    public Rides GetRideById(Long rideId) {
        return riderRepo.findById(rideId).orElse(null);
    }

    public void AddRide(Rides ride) {
        riderRepo.save(ride);
    }

    public void DeleteRide(Long rideId) {
        riderRepo.deleteById(rideId);
    }

    public void UpdateRide(Rides ride) {
        riderRepo.save(ride);
    }






}
