package mongo.example;

import java.time.LocalDate;

public record Reviews(
        int listingId,
        LocalDate date
) {}

