package com.example.researchproject.application.ports.out;

import com.example.researchproject.domain.models.Ride2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Ride2Repository extends JpaRepository<Ride2, String>{

 Optional<Ride2> findById(String rideId);
}


