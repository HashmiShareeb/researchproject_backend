package com.example.researchproject.application.ports.out.repositories;

import com.example.researchproject.domain.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
//dont forget to use Entity to extend with JPA
public interface VehicleRepository extends JpaRepository<Vehicle, String> {



}
