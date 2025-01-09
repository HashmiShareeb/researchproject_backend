package com.example.researchproject.application.ports.in;

import com.example.researchproject.domain.models.Ride;

import java.util.List;

public interface RideUseCase {
    Ride CreateRide(Ride ride);

    //get rides by driver id
    Ride getRidesByDriverId(Long driverId);

    //get rides
    List<Ride> GetRides();

    //get rides by rider id
    Ride GetRideById(Long riderId);

    //delete ride
    void DeleteRide(Long rideId);
    //update ride
    Ride UpdateRide(Ride ride);
    //get ride details
    Ride GetRideDetails(Long rideId);
}
