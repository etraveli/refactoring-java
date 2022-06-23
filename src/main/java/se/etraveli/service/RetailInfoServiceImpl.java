package se.etraveli.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.etraveli.dto.CustomerDto;
import se.etraveli.dto.MovieRental;
import se.etraveli.dto.MovieType;
import se.etraveli.entity.CustomerMovie;

import java.util.ArrayList;
import java.util.List;

import static se.etraveli.Constant.CHILDREN_DAY;
import static se.etraveli.Constant.CHILDREN_MULTIPLY;
import static se.etraveli.Constant.DAY_NEW_ENTER_POINT;
import static se.etraveli.Constant.NEW_MULTIPLY;
import static se.etraveli.Constant.REGULAR_DAY;
import static se.etraveli.Constant.REGULAR_MULTIPLY;

@Slf4j
@Service
@RequiredArgsConstructor
public class RetailInfoServiceImpl implements RetailInfoService {

    private final CustomerService customerService;
    private final CustomerMovieService customerMovieService;
    private final MovieService movieService;
    private final MessageSourceService messageSourceService;

    @Override
    public String statement(CustomerDto customerDto) {
        log.info(messageSourceService.logMessage("statement", customerDto));
        var customer = customerService.findCustomerByName(customerDto.getName());
        List<CustomerMovie> customerMovies = new ArrayList<>();
        double totalAmount = 0.0;
        int frequentEnterPoints = 0;
        StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");

        for (MovieRental movieRental: customerDto.getRentals()) {
            var movie = movieService.getMovieByCode(movieRental.getMovieId());
            var amount = movie.getMovieType().getPrice();

            if (movie.getMovieType().equals(MovieType.NEW)) {
                amount = movieRental.getDays() * NEW_MULTIPLY;
                if (movieRental.getDays() > DAY_NEW_ENTER_POINT) {
                    frequentEnterPoints++;
                }
            }
            else if (movie.getMovieType().equals(MovieType.REGULAR)) {
                if (movieRental.getDays() > REGULAR_DAY) {
                    amount += (movieRental.getDays() - REGULAR_DAY) * REGULAR_MULTIPLY;
                }
            }
            else if (movie.getMovieType().equals(MovieType.CHILDRENS)) {
                if (movieRental.getDays() > CHILDREN_DAY) {
                    amount += (movieRental.getDays() - CHILDREN_DAY) * CHILDREN_MULTIPLY;
                }
            }
            var customerMovie = new CustomerMovie();
            customerMovie.setCustomer(customer);
            customerMovie.setMovie(movie);
            customerMovie.setPrice(amount);
            customerMovies.add(customerMovie);
            log.info(messageSourceService.logMessage("save.customer.movie", customerMovie));
            totalAmount += amount;
            frequentEnterPoints++;

            result
                    .append("\t")
                    .append(movie.getTitle())
                    .append("\t")
                    .append(amount)
                    .append("\n");
        }
        customerMovieService.createCustomerMovieAll(customerMovies);
        result
                .append("Amount owed is ")
                .append(totalAmount)
                .append("\n")
                .append("You earned ")
                .append(frequentEnterPoints)
                .append(" frequent points\n");

        return result.toString();
    }
}
