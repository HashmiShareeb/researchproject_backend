package com.example.researchproject.application.ports.out;

import com.example.researchproject.domain.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//dont forget to use Entity to extend with JPA

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    boolean existsByLicensePlate(String licencePlate);



}
