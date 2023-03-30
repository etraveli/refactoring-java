package com.junjie.movie.rental;

import com.google.common.collect.Lists;
import com.junjie.movie.rental.dto.CustomerDto;
import com.junjie.movie.rental.dto.MovieDto;
import com.junjie.movie.rental.dto.MovieRentalDto;
import com.junjie.movie.rental.dto.OrderDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderTest extends BaseIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @BeforeAll
    public void insertTestData() throws URISyntaxException {
        var insertMovieUri = "http://localhost:" + port + "/movie";
        restTemplate.exchange(new URI(insertMovieUri),
                HttpMethod.POST,
                new HttpEntity<>(contructMovieRequest()),
                new ParameterizedTypeReference<>() {});

        var insertCustomerUri = "http://localhost:" + port + "/customer";
        restTemplate.exchange(new URI(insertCustomerUri),
                HttpMethod.POST,
                new HttpEntity<>(contructCustomerRequest()),
                new ParameterizedTypeReference<>() {});
    }

    private CustomerDto contructCustomerRequest() {
        var customerDto = new CustomerDto();
        customerDto.setName("Junjie Feng");
        return customerDto;
    }

    private List<MovieDto> contructMovieRequest() {
        return Lists.newArrayList(contructSingleMovie("You've Got Mail", "regular"),
                contructSingleMovie("Matrix", "Regular"),
                contructSingleMovie("Cars", "children"),
                contructSingleMovie("Fast & Furious X", "NEW"));
    }

    private MovieDto contructSingleMovie(String name, String type) {
        var movieDto = new MovieDto();
        movieDto.setName(name);
        movieDto.setType(type);
        return  movieDto;
    }

    @Test
    public void testCreateOrder() throws URISyntaxException {
        var createOrderUri = "http://localhost:" + port + "/order";
        ResponseEntity<OrderDto> orderResponse =
                restTemplate.exchange(new URI(createOrderUri),
                        HttpMethod.POST,
                        new HttpEntity<>(contructOrderRequest()),
                        new ParameterizedTypeReference<>() {});
        System.out.println("the result is: " + orderResponse.getStatusCode());
//        Assertions.assertEquals(1L, movieResponse.getBody().getCustomerId());
        Assertions.assertEquals(11.5, orderResponse.getBody().getTotalExpense());
        Assertions.assertEquals(3, orderResponse.getBody().getOrderFrequentPoints());
        Assertions.assertEquals(3, orderResponse.getBody().getMovieRentalDtoList().size());
        Assertions.assertEquals(1L, orderResponse.getBody().getMovieRentalDtoList().get(0).getMovieId());
        Assertions.assertEquals(3, orderResponse.getBody().getMovieRentalDtoList().get(0).getRentDays());
        Assertions.assertEquals(3.5, orderResponse.getBody().getMovieRentalDtoList().get(0).getExpense());
        Assertions.assertEquals(2L, orderResponse.getBody().getMovieRentalDtoList().get(1).getMovieId());
        Assertions.assertEquals(2, orderResponse.getBody().getMovieRentalDtoList().get(1).getRentDays());
        Assertions.assertEquals(2.0, orderResponse.getBody().getMovieRentalDtoList().get(1).getExpense());
        Assertions.assertEquals(4L, orderResponse.getBody().getMovieRentalDtoList().get(2).getMovieId());
        Assertions.assertEquals(2, orderResponse.getBody().getMovieRentalDtoList().get(2).getRentDays());
        Assertions.assertEquals(6.0, orderResponse.getBody().getMovieRentalDtoList().get(2).getExpense());
    }

    private OrderDto contructOrderRequest() {
        var orderDto = new OrderDto();
        orderDto.setCustomerId(1L);
        orderDto.setMovieRentalDtoList(Lists.newArrayList(constructMovieRetanlDto(1L, 3),
                constructMovieRetanlDto(2L, 2),
                constructMovieRetanlDto(4L, 2)));
        return orderDto;
    }

    private MovieRentalDto constructMovieRetanlDto(long movieId, int rentDays) {
        var movieRentalDto = new MovieRentalDto();
        movieRentalDto.setMovieId(movieId);
        movieRentalDto.setRentDays(rentDays);
        return movieRentalDto;
    }
}
