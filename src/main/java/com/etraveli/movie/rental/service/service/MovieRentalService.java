package com.etraveli.movie.rental.service.service;

import com.etraveli.movie.rental.service.configuration.MovieRentalConfiguration;
import com.etraveli.movie.rental.service.configuration.MovieRentalConfiguration.MovieCode;
import com.etraveli.movie.rental.service.configuration.MovieRentalConfiguration.RentalFee;
import com.etraveli.movie.rental.service.dto.MovieRentalRequest;
import com.etraveli.movie.rental.service.dto.MovieRentalRequestLine;
import com.etraveli.movie.rental.service.dto.MovieRentalResponse;
import com.etraveli.movie.rental.service.dto.MovieRentalResponseLine;
import com.etraveli.movie.rental.service.repository.CustomerRepository;
import com.etraveli.movie.rental.service.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Setter
@Service
@RequiredArgsConstructor
public class MovieRentalService {

    private final MovieRepository movieRepository;
    private final MovieRentalConfiguration movieRentalConfiguration;
    private final CustomerRepository customerRepository;

    @Value("${default.frequentEnterPoints:1}")
    private int defaultPoints;

    @Value("${extraPoints.eligibleDays:2}")
    private int extraPointsEligibleDays;

    @Value("${extraPoints.eligibleType:NEW}")
    private MovieCode extraPointsEligibleType;

    // Create final movie rental response
    public MovieRentalResponse getMovieRentalResponse(MovieRentalRequest request) {
        List<MovieRentalResponseLine> responseLines = createMovieRentalResponseLines(request.getRentalLines());

        return MovieRentalResponse.builder()
                .customer(customerRepository.findCustomerById(request.getCustomerId()))
                .movieRentalResponseLines(responseLines)
                .totalRental(calculateTotalAmount(responseLines))
                .frequentEnterPoints(responseLines.stream()
                        .mapToInt(MovieRentalResponseLine::frequentEnterPoints)
                        .sum())
                .build();
    }

    private List<MovieRentalResponseLine> createMovieRentalResponseLines(List<MovieRentalRequestLine> rentalRequestLines) {

        return rentalRequestLines.stream()
                .map(this::createMovieRentalResponseLine)
                .toList();
    }

    // Create movie rental response lines data
    private MovieRentalResponseLine createMovieRentalResponseLine(MovieRentalRequestLine rentalRequestLine) {

        return movieRepository.findMovieById(rentalRequestLine.movieId())
                .map(movie -> {
                    BigDecimal rental = calculateLineAmount(
                            rentalRequestLine.noOfDays(), movieRentalConfiguration.findRentalFeeByMovieCode(movie.movieCode())
                    );

                    int points = calculateFrequentEnterPoints(movie.movieCode(), rentalRequestLine.noOfDays());

                    return MovieRentalResponseLine.builder()
                            .movieName(movie.title())
                            .rental(rental)
                            .frequentEnterPoints(points)
                            .build();
                })
                .orElseThrow(() -> new NoSuchElementException("Movie not found with movie id: " + rentalRequestLine.movieId()));

    }

    // Calculate rental per each movie rental response line
    private BigDecimal calculateLineAmount(int noOfDays, RentalFee rentalFee) {
        return (noOfDays > rentalFee.startingFeeEligibleDays()) ?
                rentalFee.startingFee().add(rentalFee.dailyFee().multiply(BigDecimal.valueOf(noOfDays - rentalFee.startingFeeEligibleDays()))) :
                rentalFee.startingFee();
    }

    // Calculate total rental for all movie rental response lines
    private BigDecimal calculateTotalAmount(List<MovieRentalResponseLine> movieRentalResponseLines) {
        return movieRentalResponseLines.stream()
                .map(MovieRentalResponseLine::rental)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Calculate frequentEnterPoints for each lines
    private int calculateFrequentEnterPoints(MovieCode movieCode, int days) {
        return defaultPoints + (
                movieCode.equals(extraPointsEligibleType) &&
                        (days > extraPointsEligibleDays) ? defaultPoints : 0
        );
    }
}
