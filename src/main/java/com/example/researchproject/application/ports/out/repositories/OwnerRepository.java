package com.example.researchproject.application.ports.out.repositories;

import com.example.researchproject.domain.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, String> {

    boolean existsByEmail(String email);

   //Getvehible by owner
   //Owner getVehicle(String ownerId);
}
