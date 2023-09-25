package com.etraveli.movie.rental.service.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;
import java.util.List;


@ConfigurationProperties(prefix = "com.etraveli.movie.rental")
public record MovieRentalConfiguration(List<RentalFee> rentalFee) {

    public enum MovieCode {
        NEW,
        REGULAR,
        CHILDREN
    }

    public record RentalFee(
            MovieCode movieCode,
            BigDecimal startingFee,
            int startingFeeEligibleDays,
            BigDecimal additionalDailyFee) {
    }
}

