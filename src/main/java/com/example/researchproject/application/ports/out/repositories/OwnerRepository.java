package com.example.researchproject.application.ports.out.repositories;

import com.example.researchproject.domain.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, String> {

    boolean existsByEmail(String email);
}
