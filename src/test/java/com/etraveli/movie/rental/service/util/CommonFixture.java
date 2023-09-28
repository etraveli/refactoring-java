package com.etraveli.movie.rental.service.util;

import com.etraveli.movie.rental.service.configuration.MovieRentalConfiguration;
import com.etraveli.movie.rental.service.dto.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class CommonFixture {

    public static MovieRentalRequest createMovieRentalRequest(String customerId) {
        MovieRentalRequest request = new MovieRentalRequest();
        request.setCustomerId(customerId);

        List<MovieRentalRequestLine> rentalRequestLines = Arrays.asList(
                new MovieRentalRequestLine("F001", 2),
                new MovieRentalRequestLine("F002", 1)
        );
        request.setRentalLines(rentalRequestLines);

        return request;
    }

    public static MovieRentalConfiguration.RentalFee createRentalFee() {
        return new MovieRentalConfiguration.RentalFee(MovieRentalConfiguration.MovieCode.REGULAR, BigDecimal.valueOf(2), 2, BigDecimal.valueOf(1.5));
    }

    public static MovieRentalResponse createMovieRentalResponse() {
        Customer customer = Customer.builder()
                .customerId("1")
                .name("C. U. Stomer")
                .build();

        List<MovieRentalResponseLine> responseLines = Arrays.asList(
                MovieRentalResponseLine.builder()
                        .movieName("You've Got Mail")
                        .rental(BigDecimal.valueOf(2))
                        .frequentEnterPoints(2)
                        .build(),
                MovieRentalResponseLine.builder()
                        .movieName("Matrix")
                        .rental(BigDecimal.valueOf(2))
                        .frequentEnterPoints(2)
                        .build()
        );

        return MovieRentalResponse.builder()
                .customer(customer)
                .movieRentalResponseLines(responseLines)
                .totalRental(BigDecimal.valueOf(4))
                .frequentEnterPoints(4)
                .build();
    }
}
