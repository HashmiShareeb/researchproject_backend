package com.example.researchproject.application.ports.in;

import com.example.researchproject.application.ports.dto.RideDTO;
import com.example.researchproject.domain.models.Ride.Ride;

import java.util.List;

public interface RideUseCase {

    Ride CreateRide(Ride ride);

    //get ride by id
    Ride GetRideById(String rideId);

    //get all rides
    List<RideDTO> GetRides();

    //delete ride
    void deleteRide(String rideId);

    //update ride
    Ride updateRide(Ride ride);

    //request ride
    Ride RequestRide(RideDTO rideDTO, String userId, String vehicleId);

    //start ride
    Ride startRide(String rideId);

    Ride endRide(String rideId);

    List<Ride> GetRideHistory(String userId);

    //get rides by user id
    List<Ride> GetRidesByUserId(String userId);

    //cancel ride
    Ride cancelRide(String rideId);

}
