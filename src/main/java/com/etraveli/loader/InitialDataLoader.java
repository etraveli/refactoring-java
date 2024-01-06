package com.etraveli.loader;


import com.etraveli.constant.CodesEnum;
import com.etraveli.modal.MovieRental;
import com.etraveli.modal.request.CustomerRequest;
import com.etraveli.modal.request.MovieRentalRequest;
import com.etraveli.modal.request.MovieRequest;
import com.etraveli.modal.response.CustomerResponse;
import com.etraveli.modal.response.MovieResponse;
import com.etraveli.service.CustomerService;
import com.etraveli.service.MovieService;
import com.etraveli.service.RentalInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitialDataLoader {
    private static final Logger logger = LoggerFactory.getLogger(InitialDataLoader.class);

    private final CustomerService customerService;
    private final MovieService movieService;
    private final RentalInfoService rentalInfoService;

    @Autowired
    public InitialDataLoader(CustomerService customerService, MovieService movieService, RentalInfoService rentalInfoService) {
        this.customerService = customerService;
        this.movieService = movieService;
        this.rentalInfoService = rentalInfoService;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("--ENTER-- onApplicationEvent(ContextRefreshedEvent)");
        try {
            this.loadCustomerDataToDatabase();
            this.loadMovieDataToDatabase();
            this.loadMovieRentalDataToDatabase();
        } catch (Exception ex) {
            logger.error("--ERROR-- onApplicationEvent -- Data not save data to the Database--");
        }
        logger.info("--EXIT-- onApplicationEvent--");
    }

    private void loadMovieDataToDatabase() {
        logger.info("--ENTER-- loadMovieDataToDatabase--");

        List<MovieResponse> allMovies = this.movieService.getAllMovies();

        if (allMovies.size() == 0) {
            List<MovieRequest> movieList = new ArrayList<>();

            MovieRequest movieRequest1 = new MovieRequest();
            movieRequest1.setMovieId(1L);
            movieRequest1.setCode(CodesEnum.REGULAR.getValue());
            movieRequest1.setMovieCode("F001");
            movieRequest1.setTitle("You've Got Mail");

            MovieRequest movieRequest2 = new MovieRequest();
            movieRequest2.setMovieId(2L);
            movieRequest2.setCode(CodesEnum.REGULAR.getValue());
            movieRequest2.setMovieCode("F002");
            movieRequest2.setTitle("Matrix");

            MovieRequest movieRequest3 = new MovieRequest();
            movieRequest3.setMovieId(3L);
            movieRequest3.setCode(CodesEnum.CHILDRENS.getValue());
            movieRequest3.setMovieCode("F003");
            movieRequest3.setTitle("Cars");

            MovieRequest movieRequest4 = new MovieRequest();
            movieRequest4.setMovieId(4L);
            movieRequest4.setCode(CodesEnum.NEW.getValue());
            movieRequest4.setMovieCode("F004");
            movieRequest4.setTitle("Fast & Furious X");

            movieList.add(movieRequest1);
            movieList.add(movieRequest2);
            movieList.add(movieRequest3);
            movieList.add(movieRequest4);

            for (MovieRequest movieRequest : movieList) {
                this.movieService.createMovie(movieRequest);
            }
        }
        logger.info("--EXIT-- loadMovieDataToDatabase--");
    }

    private void loadCustomerDataToDatabase() {
        logger.info("--ENTER-- loadCustomerDataToDatabase--");
        List<CustomerResponse> allCustomers = this.customerService.getAllCustomers();

        if (allCustomers.size() == 0) {

            CustomerRequest customer = new CustomerRequest();
            customer.setCustomerId(1L);
            customer.setName("C. U. Stomer");
            customer.setBirthYear(1989);
            customer.setIdNumber("19890101-1234");

            this.customerService.createCustomer(customer);
        }
        logger.info("--EXIT-- loadCustomerDataToDatabase--");
    }


    private void loadMovieRentalDataToDatabase() {
        logger.info("--ENTER-- loadMovieRentalDataToDatabase--");

        List<MovieRental> movieRentalList = this.rentalInfoService.getAllMovieRentals();

        if (movieRentalList.size() == 0) {
            List<MovieRentalRequest> rentalRequestList = new ArrayList<>();

            MovieRentalRequest movieRentalRequest1 = new MovieRentalRequest();
            movieRentalRequest1.setCustomerId(1L);
            movieRentalRequest1.setMovieCode("F001");
            movieRentalRequest1.setDays(3);

            MovieRentalRequest movieRentalRequest2 = new MovieRentalRequest();
            movieRentalRequest2.setCustomerId(1L);
            movieRentalRequest2.setMovieCode("F002");
            movieRentalRequest2.setDays(1);

            rentalRequestList.add(movieRentalRequest1);
            rentalRequestList.add(movieRentalRequest2);

            for (MovieRentalRequest rentalRequest : rentalRequestList) {
                this.rentalInfoService.createMovieRental(rentalRequest);
            }
        }
        logger.info("--EXIT-- loadMovieRentalDataToDatabase--");
    }
}
