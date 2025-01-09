package com.example.researchproject.application.ports.out.repositories;

import com.example.researchproject.domain.models.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {


}
