package com.example.researchproject.application.ports.in;

import com.example.researchproject.domain.models.Rides;

import java.util.List;

public interface RideUseCase {
    Rides CreateRide(Rides ride);

    //get rides by driver id
    Rides getRidesByDriverId(Long driverId);

    //get rides
    List<Rides> GetRides();

    //get rides by rider id
    Rides GetRideById(Long riderId);

    //delete ride
    void DeleteRide(Long rideId);
    //update ride
    Rides UpdateRide(Rides ride);
    //get ride details
    Rides GetRideDetails(Long rideId);
}
