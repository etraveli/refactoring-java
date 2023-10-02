package com.etraveli.movie.rental.service.service;

import com.etraveli.movie.rental.service.entities.Movie;
import com.etraveli.movie.rental.service.configuration.RentalDetailConfiguration;
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
    //private final MovieRentalRepository movieRentalRepository;
    @Autowired
    private final CustomerService customerService;

    @Autowired
    private final MovieService movieService;

    @Autowired
    private final RentalDetailConfiguration rentalDetailConfiguration;

    @Setter
    @Value("${base.frequentEnterPoints:1}")
    private int basePoints;

    @Setter
    @Value("${bonusPoints.applicableDays:2}")
    private int daysToEarnBonusPoints;

    @Setter
    @Value("${bonusPoints.applicableGenre:'NEW'}")
    private String genreToEarnBonusPoints;

//
//    @Override
//    public RentalDetailsResponse getCustomerRental(RentalDetailsRequest request) {
//        return null;
//    }

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
                .rent(getLineAmount(lineDetails.noOfDays(), rentalDetailConfiguration.rentalPriceByMovieGenre(movie.getGenre().toUpperCase())))
                .frequentEnterPoints(getFrequentEnterPoints(movie.getGenre(), lineDetails.noOfDays())).build();
    }

    public BigDecimal getLineAmount(int noOfDays, RentalPrice rentalPrice) {
        return (noOfDays > rentalPrice.noOfDaysForBasePrice()) ?
                rentalPrice.basePrice().add(rentalPrice.perDayPrice().multiply(BigDecimal.valueOf(noOfDays - rentalPrice.noOfDaysForBasePrice()))):
                rentalPrice.basePrice();
    }

    public int getFrequentEnterPoints(String movieGenre, int noOfDays) {
        return basePoints + (movieGenre.equalsIgnoreCase(genreToEarnBonusPoints) && (noOfDays > daysToEarnBonusPoints) ? basePoints : 0);
    }

    public BigDecimal getTotalRentAmount(List<RentalLineDetailsResponse> lineDetails) {
        return lineDetails.stream().map(RentalLineDetailsResponse::rent)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

//    public RentalDetailsResponse statement(RentalDetailsRequest re) {
//        BigDecimal totalAmount = BigDecimal.ZERO;
//        int frequentEnterPoints = 0;
//        ArrayList<RentalLineDetailsResponse> rentaldetails = new ArrayList<>();
//        for (RentalLineDetailsRequest r : re.getRentalDetailsRequestList()) {
//            Movie movie = movieService.findById(r.movieId());
//            BigDecimal thisAmount = BigDecimal.ZERO;
//            int eachFrequentEnterPoints = 1;
//            switch (movie.getGenre()) {
//                case "regular":
//                    thisAmount = BigDecimal.valueOf(2);
//                    if (r.noOfDays() > 2) {
//                        thisAmount = thisAmount.add(BigDecimal.valueOf(r.noOfDays() - 2).multiply(BigDecimal.valueOf(1.5)));
//                    }
//                    break;
//                case "new":
//                    thisAmount = BigDecimal.valueOf(r.noOfDays() * 3);
//                    // Add bonus for a two-day new release rental
//                    if (r.noOfDays() > 2) {
//                        eachFrequentEnterPoints = 2;
//                    }
//                    break;
//                case "childrens":
//                    thisAmount = BigDecimal.valueOf(1.5);
//                    if (r.noOfDays() > 3) {
//                        thisAmount = thisAmount.add(BigDecimal.valueOf(r.noOfDays() - 3).multiply(BigDecimal.valueOf(1.5)));
//                    }
//                    break;
//            }
//
//            frequentEnterPoints += eachFrequentEnterPoints;
//            RentalLineDetailsResponse rentalLineDetailsResponse =
//                    RentalLineDetailsResponse.builder()
//                            .movieName(movie.getTitle())
//                            .frequentEnterPoints(eachFrequentEnterPoints)
//                            .rent(thisAmount)
//                            .build();
//            rentaldetails.add(rentalLineDetailsResponse);
//            totalAmount = totalAmount.add(thisAmount);
//        }
//        return RentalDetailsResponse.builder()
//                .totalRent(totalAmount)
//                .rentalLineDetailsResponse(rentaldetails)
//                .customer(customerService.getCustomerByCustomerId(re.getCustomerId()))
//                .frequentEnterPoints(frequentEnterPoints)
//                .build();
//    }
}
