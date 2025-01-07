package com.example.researchproject.domain.models.enums;

public enum RideStatus {
    AVAILABLE,
    OCCUPIED,
    IN_TRANSIT,
    MAINTENANCE,
    OUT_OF_SERVICE;


    public String rideStatus(RideStatus rideStatus) {
       switch (rideStatus) {
            case AVAILABLE:
                return "Available";
            case OCCUPIED:
                return "Occupied";
            case IN_TRANSIT:
                return "In Transit";
            case MAINTENANCE:
                return "Maintenance";
            case OUT_OF_SERVICE:
                return "Out of Service";
            default:
                return "Unknown";
        }
    }


}



