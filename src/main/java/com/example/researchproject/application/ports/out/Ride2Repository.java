package com.example.researchproject.application.ports.out;

import com.example.researchproject.domain.models.Ride2;
import com.example.researchproject.domain.models.enums.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Ride2Repository extends JpaRepository<Ride2, String>{

 Optional<Ride2> findById(String rideId);

 List<Ride2> findByUser_UserIdAndRideStatus(String userId, RideStatus rideStatus);

}


