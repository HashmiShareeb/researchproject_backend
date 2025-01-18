package com.example.researchproject.application.ports.in;

import com.example.researchproject.domain.models.Ride;
import com.example.researchproject.domain.models.User;
import com.example.researchproject.domain.models.enums.RideStatus;

import java.util.List;

public interface RideUseCase {
    Ride CreateRide(Ride ride);

    //get rides by driver id
    Ride getRidesByDriverId(String driverId);

    //get rides
    List<Ride> GetRides();

    //get rides by rider id
    Ride GetRideById(String riderId);

    //delete ride
    void DeleteRide(String rideId);
    //update ride
    Ride UpdateRide(Ride ride);
    //get ride details
    Ride GetRideDetails(String rideId);

    //find rides by user
    List<Ride> findByUser(User user);

    //update ride status
    Ride updateRideStatus(String rideId, RideStatus status);

    //get ride history
    List<Ride> getRideHistory(String username);
}
