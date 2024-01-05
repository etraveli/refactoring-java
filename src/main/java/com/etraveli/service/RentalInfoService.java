package com.etraveli.service;

import com.etraveli.modal.request.MovieRentalRequest;
import com.etraveli.modal.response.MovieRentalResponse;
import org.springframework.stereotype.Service;

@Service
public interface RentalInfoService {
    String statement(Long customer);
    MovieRentalResponse createMovieRental(MovieRentalRequest movieRentalRequest);
}
