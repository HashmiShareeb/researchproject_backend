package com.example.researchproject.domain.models.Vehicle;

public enum VehichleStatus {
    AVAILABLE,
    IN_USE,
    MANTAINANCE,
    OUT_OF_SERVICE;

    public String vehichleStatus(VehichleStatus vehichleStatus) {
        return switch (vehichleStatus) {
            case AVAILABLE -> "Available";
            case IN_USE -> "In Use";
            case MANTAINANCE -> "Maintenance";
            case OUT_OF_SERVICE -> "Out of Service";
            default -> "Unknown";
        };
    }

}
