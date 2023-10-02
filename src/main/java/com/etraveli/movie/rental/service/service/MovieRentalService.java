package com.etraveli.movie.rental.service.service;

import com.etraveli.movie.rental.service.request.RentalDetailsRequest;
import com.etraveli.movie.rental.service.response.RentalDetailsResponse;


public interface MovieRentalService {
    RentalDetailsResponse getCustomerRental(RentalDetailsRequest request);
}
