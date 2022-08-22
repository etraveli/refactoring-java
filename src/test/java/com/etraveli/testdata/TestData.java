package com.etraveli.testdata;

import com.etraveli.db.entity.MovieEntity;
import com.etraveli.model.Customer;
import com.etraveli.model.MovieRental;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestData {

    private TestData() {

    }

    public static final String CUSTOMER_RENTAL_INFORMATION = "[{\"name\":\"Rental Record for\",\"value\":\"C. U. Stomer\"}," +
            "{\"name\":\"You have got mail\",\"value\":\"3.5\"}," +
            "{\"name\":\"Matrix\",\"value\":\"2.0\"}," +
            "{\"name\":\"Amount owed is\",\"value\":\"5.5\"}," +
            "{\"name\":\"You earned\",\"value\":\"2 frequent points\"}]";

    public static Customer getCustomerWithRentals() {
        return new Customer("C. U. Stomer",
                Arrays.asList(new MovieRental("F001", 3),
                        new MovieRental("F002", 1)));
    }

    public static Customer getCustomerWithoutRentals() {
        return new Customer("C. U. Stomer", null);
    }

    public static List<MovieEntity> getMovies() {
        List<MovieEntity> movieEntities = new ArrayList<>();
        movieEntities.add(createMovieEntity("Regular", "", "F001"));
        movieEntities.add(createMovieEntity("Children", "", "F002"));
        movieEntities.add(createMovieEntity("New", "", "F003"));
        return movieEntities;
    }

    private static MovieEntity createMovieEntity(String code, String title, String id) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setCode(code);
        movieEntity.setTitle(title);
        movieEntity.setId(id);
        return movieEntity;
    }

}
