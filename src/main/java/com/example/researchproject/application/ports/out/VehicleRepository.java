package com.example.researchproject.application.ports.out;

import com.example.researchproject.application.ports.dto.VehicleDTO;
import com.example.researchproject.domain.models.Vehicle;
import com.example.researchproject.domain.models.enums.VehichleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
//dont forget to use Entity to extend with JPA

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    boolean existsByLicensePlate(String licencePlate);

    List<VehicleDTO>findByStatus(String status);

    List<Vehicle> findByVehicleStatus(VehichleStatus vehicleStatus);

}
