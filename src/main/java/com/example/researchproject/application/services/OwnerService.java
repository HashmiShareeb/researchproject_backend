package com.example.researchproject.application.services;

import com.example.researchproject.application.ports.out.OwnerRepository;
import com.example.researchproject.domain.models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepo;

    public List<Owner> getOwners() {
        return ownerRepo.findAll(); // Ensure this is returning data
    }

    public Owner CreateOwner(Owner owner) {

        if (ownerRepo.existsByEmail(owner.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + owner.getEmail());
        }
        return ownerRepo.save(owner);
    }

    public Owner GetOwnerById(String ownerId) {
        return ownerRepo.findById(ownerId).orElse(null);
    }

    public void DeleteOwner(String ownerId) {
        ownerRepo.deleteById(ownerId);
    }

    public Owner findById(String ownerId) {
        return ownerRepo.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("Owner not found with ID: " + ownerId));
    }

    //owner getVehicle
    public Owner GetVehicle(String ownerId) {
        return ownerRepo.findById(ownerId).orElse(null);
    }



}
