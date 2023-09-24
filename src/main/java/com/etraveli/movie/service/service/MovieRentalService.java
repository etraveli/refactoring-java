package com.etraveli.movie.service.service;

import com.etraveli.movie.service.application.DataHolder;
import com.etraveli.movie.service.dto.Movie;
import com.etraveli.movie.service.dto.MovieType;
import com.etraveli.movie.service.dto.RentOrder;
import com.etraveli.movie.service.dto.RentOrderLine;
import com.etraveli.movie.service.dto.RentOrderResponse;
import com.etraveli.movie.service.dto.RentalFee;
import com.etraveli.movie.service.repository.CustomerRepository;
import com.etraveli.movie.service.repository.MovieRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieRentalService {

    private final MovieRepository movieRepository;
    private final CustomerRepository customerRepository;
    private final DataHolder staticDataHolder;

    public RentOrderResponse createRentOrderResponse(RentOrder order) {
        List<RentOrderLine> orderLineList = createOrderLineList(order.orderLineList());

        return RentOrderResponse.builder()
                .customer(customerRepository.findCustomerByID(order.customerID()))
                .rentOrderLineList(orderLineList)
                .totalRental(orderLineList.stream()
                        .map(RentOrderLine::rentAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .build();
    }

    private List<RentOrderLine> createOrderLineList(List<RentOrderLine> orderLineList) {
        List<RentOrderLine> rentOrderResponseLineList = new ArrayList<>();
        for (RentOrderLine rentOrderLine : orderLineList) {
            Movie movie = movieRepository.findMovieByID(rentOrderLine.movieCode());

            BigDecimal rental = calculateLineRental(rentOrderLine.rentDays(), movie.type());
            rentOrderResponseLineList.add(RentOrderLine.builder()
                    .movieName(movie.movieName())
                    .rentDays(rentOrderLine.rentDays())
                    .rentAmount(rental)
                    .build());
        }
        return rentOrderResponseLineList;
    }

    private BigDecimal calculateLineRental(int days, MovieType type) {
        RentalFee rentalFee = staticDataHolder.getRentalFeeByCode(type);
        return switch (type) {
            case REGULAR, CHILDREN ->
                    days > rentalFee.flatFeeLimit() ? (rentalFee.dailyFee()).multiply(BigDecimal.valueOf(days - rentalFee.flatFeeLimit())).add(rentalFee.flatFee()) : rentalFee.flatFee();
            case NEW -> rentalFee.dailyFee().multiply(BigDecimal.valueOf(days));
        };
    }
}