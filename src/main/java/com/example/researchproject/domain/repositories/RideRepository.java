package com.example.researchproject.domain.repositories;

import com.example.researchproject.domain.models.Rides;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RideRepository extends JpaRepository<Rides, Long> {



}
