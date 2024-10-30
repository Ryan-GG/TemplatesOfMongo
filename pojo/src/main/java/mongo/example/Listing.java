package mongo.example;

import java.time.LocalDate;

public record Listing(
        int id,
        String name,
        int hostId,
        String hostName,
        String neighbourhoodGroup,
        String neighbourhood,
        double latitude,
        double longitude,
        String roomType,
        int price,
        int minimumNights,
        int numberOfReviews,
        LocalDate lastReview,
        double reviewsPerMonth,
        int calculatedHostListingsCount,
        int availability365,
        int numberOfReviewsLtm,
        String license
) {}