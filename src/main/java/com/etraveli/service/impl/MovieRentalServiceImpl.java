package com.etraveli.service.impl;

import com.etraveli.constant.CodesEnum;
import com.etraveli.exceptiondomain.DataNotFoundException;
import com.etraveli.exceptiondomain.constant.ExceptionConstant;
import com.etraveli.modal.Customer;
import com.etraveli.modal.Movie;
import com.etraveli.modal.MovieRental;
import com.etraveli.modal.request.MovieRentalRequest;
import com.etraveli.modal.response.MovieRentalResponse;
import com.etraveli.repository.MovieRentalRepository;
import com.etraveli.service.CustomerService;
import com.etraveli.service.MovieService;
import com.etraveli.service.RentalInfoService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
@Transactional
public class MovieRentalServiceImpl implements RentalInfoService {
    private final Logger logger = getLogger(this.getClass());

    private final MovieRentalRepository movieRentalRepository;
    private final MovieService movieService;
    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    @Autowired
    public MovieRentalServiceImpl(MovieRentalRepository movieRentalRepository, MovieService movieService, CustomerService customerService, ModelMapper modelMapper) {
        this.movieRentalRepository = movieRentalRepository;
        this.movieService = movieService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @Override
    public String statement(Long customerId) {
        logger.info("--ENTER--[GET]--statement--{}", customerId);

        Customer customer = this.customerService.getCustomerByCustomerId(customerId);

        if (customer == null) {
            logger.info("--ERROR--[GET]--DataNotFoundException--{}", ExceptionConstant.CUSTOMER_NOT_FOUND);
            throw new DataNotFoundException(ExceptionConstant.CUSTOMER_NOT_FOUND);
        }

        double totalAmount = 0;
        int frequentEnterPoints = 0;
        StringBuilder result = new StringBuilder("Rental Record for ").append(customer.getName()).append("\n");

        for (MovieRental r : customer.getRentals()) {
            double thisAmount = calculateRentalAmount(r);

            // add frequent bonus points
            frequentEnterPoints++;

            // add bonus for a two-day new release rental
            if (CodesEnum.NEW.getValue().equals(r.getMovie().getCode()) && r.getDays() > 2) {
                frequentEnterPoints++;
            }

            // print figures for this rental
            result.append("\t").append(r.getMovie().getTitle()).append("\t").append(thisAmount).append("\n");
            totalAmount += thisAmount;
        }

        // add footer lines
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentEnterPoints).append(" frequent points\n");

        return result.toString();
    }

    private double calculateRentalAmount(MovieRental rental) {
        logger.info("--ENTER--calculateRentalAmount--");
        Movie movie = rental.getMovie();
        double thisAmount;

        // determine amount for each movie
        switch (CodesEnum.valueOf(movie.getCode().toUpperCase())) {
            case REGULAR -> {
                thisAmount = 2;
                if (rental.getDays() > 2) {
                    thisAmount += (rental.getDays() - 2) * 1.5;
                }
            }
            case NEW -> thisAmount = rental.getDays() * 3;
            case CHILDRENS -> {
                thisAmount = 1.5;
                if (rental.getDays() > 3) {
                    thisAmount += (rental.getDays() - 3) * 1.5;
                }
            }
            default -> {
                logger.error("--ERROR--calculateRentalAmount--DataNotFoundException--{}", ExceptionConstant.CODE_WRONG);
                throw new DataNotFoundException(ExceptionConstant.CODE_WRONG);
            }
        }
        logger.info("--EXIT--calculateRentalAmount--");
        return thisAmount;
    }

    @Override
    public MovieRentalResponse createMovieRental(MovieRentalRequest movieRentalRequest) {
        logger.info("--ENTER--createMovieRental--");

        Movie movie = null;
        Customer customer = null;

        if (movieRentalRequest != null && movieRentalRequest.getCustomerId() != null) {
            customer = this.customerService.getCustomerByCustomerId(movieRentalRequest.getCustomerId());
            if (customer == null) {
                logger.error("--ERROR--createMovieRental--DataNotFoundException--{}", ExceptionConstant.CUSTOMER_NOT_FOUND);
                throw new DataNotFoundException(ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
        }

        if (movieRentalRequest != null && movieRentalRequest.getMovieCode() != null) {
            movie = this.movieService.findMovieByMovieCode(movieRentalRequest.getMovieCode());
            if (movie == null) {
                logger.error("--ERROR--createMovieRental--DataNotFoundException--{}", ExceptionConstant.MOVIE_NOT_FOUND);
                throw new DataNotFoundException(ExceptionConstant.MOVIE_NOT_FOUND);
            }
        }

        MovieRental movieRental = this.mapRequestToEntity(movieRentalRequest);
        movieRental.setCustomer(customer);
        movieRental.setMovie(movie);

        MovieRental savedMovieRental = this.movieRentalRepository.save(movieRental);

        logger.info("--EXIT--createMovieRental--");
        return this.mapEntityToResponse(savedMovieRental);
    }

    @Override
    public List<MovieRental> getAllMovieRentals() {
        return this.movieRentalRepository.findAll();
    }

    private MovieRental mapRequestToEntity(MovieRentalRequest movieRentalRequest) {
        return modelMapper.map(movieRentalRequest, MovieRental.class);
    }

    private MovieRentalResponse mapEntityToResponse(MovieRental movieRental) {
        return modelMapper.map(movieRental, MovieRentalResponse.class);
    }
}
