package com.etraveli.pattern;

import com.etraveli.pattern.types.MovieRentalType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieRentals {

    private final MovieRentalType newMovieType;
    private final MovieRentalType regularMovieType;
    private final MovieRentalType childrenMovieType;

    private MovieRentals(@Qualifier("new") MovieRentalType newMovieType,
                         @Qualifier("regular") MovieRentalType regularMovieType,
                         @Qualifier("children") MovieRentalType childrenMovieType) {
        this.newMovieType = newMovieType;
        this.regularMovieType = regularMovieType;
        this.childrenMovieType = childrenMovieType;
    }

    /**
     * @return List of all available movie types, if a new MovieType is created - it must be included in the list.
     */
    public List<MovieRentalType> getAllMovieRentalTypes() {
        List<MovieRentalType> movieRentalTypes = new ArrayList<>();
        movieRentalTypes.add(newMovieType);
        movieRentalTypes.add(regularMovieType);
        movieRentalTypes.add(childrenMovieType);
        return movieRentalTypes;
    }
}
