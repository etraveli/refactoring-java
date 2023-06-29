package com.movies.rental.handler;

import com.movies.rental.MovieRentalRequest;
import com.movies.rental.service.CustomerService;
import com.movies.rental.service.MovieService;
import com.movies.rental.service.repository.entity.Customer;
import com.movies.rental.service.repository.entity.Movie;
import com.movies.rental.service.repository.entity.MovieRental;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.movies.rental.service.repository.entity.MovieType;
import org.springframework.stereotype.Component;

import static com.movies.rental.service.repository.entity.MovieType.REGULAR;

@Component
public class RentalInfo {

  private final CustomerService customerService;

  private final MovieService movieService;

  public RentalInfo(CustomerService customerService, MovieService movieService) {
    this.customerService = customerService;
    this.movieService = movieService;
  }

  public void statement(Customer customer, List<MovieRentalRequest> movieRentalRequests) {
    double totalAmount = 0;
    int frequentEnterPoints = customer.getBonusPoints();
    List<MovieRental> movieRentals = new ArrayList<>();

    for (MovieRentalRequest rentalInformation : movieRentalRequests) {
      List<Movie> movieList = movieService.fetchAll();
      Movie movie =
          movieList.stream()
              .filter(m -> m.getMovieId().equals(rentalInformation.getMovieId()))
              .findFirst()
              .orElse(null);

      assert movie != null;

      double thisAmount =
          calculateAmount(rentalInformation.getDaysOfRental(), movie.getGenre().name());
      frequentEnterPoints =
          calculateFrequentEnterPoints(
              rentalInformation.getDaysOfRental(), movie.getGenre().name(), frequentEnterPoints);
      totalAmount += thisAmount;

      MovieRental movieRental =
          MovieRental.builder()
              .movieId(movie.getMovieId())
              .daysOfRental(rentalInformation.getDaysOfRental())
              .amountPerMovie(thisAmount)
              .build();

      movieRentals.add(movieRental);
    }

    customer.setTotalAmount(totalAmount);
    customer.setBonusPoints(frequentEnterPoints);
    customer.setMovieRentals(movieRentals);

    customerService.save(customer);
  }

  private double calculateAmount(int days, String movieTypeName) {
    var thisAmount = 0.0;
    MovieType movieType = MovieType.getMovieType(movieTypeName);
    switch (Objects.requireNonNull(movieType)) {
      case REGULAR -> {
        thisAmount = 2;
        if (days > REGULAR.getDaysOfRental()) {
          thisAmount += (days - REGULAR.getDaysOfRental()) * REGULAR.getPricePerDay();
        }
      }
      case NEW -> thisAmount = days * MovieType.NEW.getPricePerDay();
      case CHILDRENS -> {
        thisAmount = MovieType.CHILDRENS.getAmountPerMovie();
        if (days > MovieType.CHILDRENS.getDaysOfRental()) {
          thisAmount +=
              (days - MovieType.CHILDRENS.getDaysOfRental()) * MovieType.CHILDRENS.getPricePerDay();
        }
      }
    }

    return thisAmount;
  }

  private int calculateFrequentEnterPoints(int days, String movieCode, int frequentEnterPoints) {
    frequentEnterPoints += movieCode.equals(MovieType.NEW.getMovieTypeName()) && days > 2 ? 2 : 1;
    return frequentEnterPoints;
  }
}
