package com.example.researchproject.domain.models.enums;

public enum VehichleStatus {
    AVAILABLE,
    IN_USE,
    MANTAINANCE,
    OUT_OF_SERVICE;

    public String vehichleStatus(VehichleStatus vehichleStatus) {
        switch (vehichleStatus) {
            case AVAILABLE:
                return "Available";
            case IN_USE:
                return "In Use";
            case MANTAINANCE:
                return "Maintenance";
            case OUT_OF_SERVICE:
                return "Out of Service";
            default:
                return "Unknown";
        }
    }

}
