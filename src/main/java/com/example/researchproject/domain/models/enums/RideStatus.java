package com.example.researchproject.domain.models.enums;

public enum RideStatus {
    REQUESTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED;


    public String rideStatus(RideStatus rideStatus) {
       switch (rideStatus) {
            case REQUESTED:
                return "Requested";
            case IN_PROGRESS:
                return "In Progress";
            case COMPLETED:
                return "Completed";
            case CANCELLED:
                return "Cancelled";
            default:
                return "Unknown";

        }
    }


}



