package com.etraveli.movie.rental.service.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.math.BigDecimal;
import java.util.List;

/**
 * This record is responsible for read rental fee values from application.yml file
 */
@ConfigurationProperties(prefix = "com.etraveli.movie.rental")
public record MovieRentalConfiguration(List<RentalFee> rentalFee) {

    public RentalFee findRentalFeeByMovieCode(MovieCode movieCode) {
        return rentalFee.stream()
                .filter(rentalFee -> rentalFee.movieCode().equals(movieCode))
                .findFirst()
                .orElse(null);
    }

    public enum MovieCode {
        NEW,
        REGULAR,
        CHILDREN
    }

    public record RentalFee(
            MovieCode movieCode,
            BigDecimal startingFee,
            int startingFeeEligibleDays,
            BigDecimal dailyFee) {
    }
}

