package com.etraveli.movie.rental.service.service;

import com.etraveli.movie.rental.service.entities.Movie;
import com.etraveli.movie.rental.service.entities.RentalPrice;
import com.etraveli.movie.rental.service.request.RentalDetailsRequest;
import com.etraveli.movie.rental.service.request.RentalLineDetailsRequest;
import com.etraveli.movie.rental.service.response.RentalDetailsResponse;
import com.etraveli.movie.rental.service.response.RentalLineDetailsResponse;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MovieRentalServiceImpl implements MovieRentalService {
    @Autowired
    private final CustomerService customerService;

    @Autowired
    private final MovieService movieService;

    @Setter
    @Value("${base.frequentEnterPoints:1}")
    private int basePoints;

    @Setter
    @Value("${bonusPoints.applicableDays:2}")
    private int daysToEarnBonusPoints;

    @Setter
    @Value("${bonusPoints.applicableGenre:'NEW'}")
    private String genreToEarnBonusPoints;

    @Override
    public RentalDetailsResponse getCustomerRental(RentalDetailsRequest request) {
        List<RentalLineDetailsResponse> response = getRentalLinesDetail(request.getRentalDetailsRequestList());
        return RentalDetailsResponse.builder().customer(customerService.getCustomerByCustomerId(request.getCustomerId()))
                .rentalLineDetailsResponse(response)
                .totalRent(getTotalRentAmount(response))
                .frequentEnterPoints(response.stream()
                        .mapToInt(RentalLineDetailsResponse::frequentEnterPoints).sum()).build();
    }

    public List<RentalLineDetailsResponse> getRentalLinesDetail(List<RentalLineDetailsRequest> lineDetails) {
        return lineDetails.stream().map(this::getEachRentalLineDetail).toList();
    }

    public RentalLineDetailsResponse getEachRentalLineDetail(RentalLineDetailsRequest lineDetails) {
        Movie movie = movieService.findById(lineDetails.movieId());
        return RentalLineDetailsResponse.builder().movieName(movie.getTitle())
                .rent(getLineAmount(lineDetails.noOfDays(), rentalPriceByMovieGenre(movie.getGenre().toUpperCase())))
                .frequentEnterPoints(getFrequentEnterPoints(movie.getGenre().toUpperCase(), lineDetails.noOfDays())).build();
    }

    public BigDecimal getLineAmount(int noOfDays, RentalPrice rentalPrice) {
        return (noOfDays > rentalPrice.noOfDaysForBasePrice()) ?
                rentalPrice.basePrice().add(rentalPrice.perDayPrice().multiply(BigDecimal.valueOf(noOfDays - rentalPrice.noOfDaysForBasePrice()))):
                rentalPrice.basePrice();
    }

    @Override
    public List<RentalPrice> getRentalPriceList() {
        return List.of(
                new RentalPrice("REGULAR", BigDecimal.valueOf(2), 2, BigDecimal.valueOf(1.5)),
                new RentalPrice("NEW", BigDecimal.ZERO, 0, BigDecimal.valueOf(3)),
                new RentalPrice("CHILDREN", BigDecimal.valueOf(1.5), 3, BigDecimal.valueOf(1.5))
        );
    }

    public RentalPrice rentalPriceByMovieGenre(String movieGenre) {
        return getRentalPriceList().stream().filter(rentalPrice -> rentalPrice.movieGenre().equalsIgnoreCase(movieGenre)).findFirst().orElse(null);
    }

    public int getFrequentEnterPoints(String movieGenre, int noOfDays) {
        return basePoints + (movieGenre.equalsIgnoreCase(genreToEarnBonusPoints) && (noOfDays > daysToEarnBonusPoints) ? basePoints : 0);
    }

    public BigDecimal getTotalRentAmount(List<RentalLineDetailsResponse> lineDetails) {
        return lineDetails.stream().map(RentalLineDetailsResponse::rent)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
