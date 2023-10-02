package com.etraveli.movie.rental.service.configuration;

import com.etraveli.movie.rental.service.entities.RentalPrice;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "com.etraveli.movie.rental")
public record RentalDetailConfiguration(List<RentalPrice> priceDetails) {
    public RentalPrice rentalPriceByMovieGenre(String movieGenre) {
        return priceDetails.stream().filter(rentalPrice -> rentalPrice.movieGenre().equals(movieGenre)).findFirst().orElse(null);
    }
}
