package com.example.researchproject.application.ports.out.repositories;

import com.example.researchproject.domain.models.Rides;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RideRepository extends JpaRepository<Rides, Long> {

}
