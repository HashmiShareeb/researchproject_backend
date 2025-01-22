package com.example.researchproject.application.ports.in;

import com.example.researchproject.application.ports.dto.RideDTO;
import com.example.researchproject.domain.models.Ride2;

import java.util.List;

public interface RideUseCase2 {

    Ride2 CreateRide(Ride2 ride);

    //get ride by id
    Ride2 GetRideById(String rideId);

    //get all rides
    List<RideDTO> GetRides();

    //delete ride --> not void
    void DeleteRide(String rideId);

    //update ride
    Ride2 UpdateRide(Ride2 ride);

    //request ride
    Ride2 RequestRide(RideDTO rideDTO, String userId, String vehicleId);

    //start ride
    Ride2 StartRide(String rideId);

    //get all rides with vehicle
    //List<Ride2> GetRidesWithVehicle();


}
