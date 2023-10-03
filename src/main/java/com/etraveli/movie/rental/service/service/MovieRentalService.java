package com.etraveli.movie.rental.service.service;

import com.etraveli.movie.rental.service.entities.RentalPrice;
import com.etraveli.movie.rental.service.request.RentalDetailsRequest;
import com.etraveli.movie.rental.service.response.RentalDetailsResponse;

import java.util.List;


public interface MovieRentalService {
    RentalDetailsResponse getCustomerRental(RentalDetailsRequest request);
    List<RentalPrice> getRentalPriceList();
}
