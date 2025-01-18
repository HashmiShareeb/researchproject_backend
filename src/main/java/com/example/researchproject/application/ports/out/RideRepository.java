package com.example.researchproject.application.ports.out;

import com.example.researchproject.domain.models.Ride;
import com.example.researchproject.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RideRepository extends JpaRepository<Ride, String> {

    List<Ride> findByUser(User user);

    Optional<Ride> findById(String rideId);  // Accepts String as ID
}
