package com.example.researchproject.application.ports.in;

import com.example.researchproject.application.ports.dto.RideDTO;
import com.example.researchproject.domain.models.Ride;

import java.util.List;

public interface RideUseCase {

    Ride CreateRide(Ride ride);

    //get ride by id
    Ride GetRideById(String rideId);

    //get all rides
    List<RideDTO> GetRides();

    //delete ride --> not void
    void DeleteRide(String rideId);

    //update ride
    Ride UpdateRide(Ride ride);

    //request ride
    Ride RequestRide(RideDTO rideDTO, String userId, String vehicleId);

    //start ride
    Ride StartRide(String rideId);

    //get all rides with vehicle
    //List<Ride2> GetRidesWithVehicle();

    Ride EndRide(String rideId);

    List<Ride> GetRideHistory(String userId);


}
