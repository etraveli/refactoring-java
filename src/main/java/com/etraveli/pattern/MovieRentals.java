package com.etraveli.pattern;

import com.etraveli.pattern.types.Children;
import com.etraveli.pattern.types.MovieRentalType;
import com.etraveli.pattern.types.New;
import com.etraveli.pattern.types.Regular;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MovieRentals {

    private final New newMovieType;
    private final Regular regularMovieType;
    private final Children childrenMovieType;

    public MovieRentals(New newMovieType,
                        Regular regularMovieType,
                        Children childrenMovieType) {
        this.newMovieType = newMovieType;
        this.regularMovieType = regularMovieType;
        this.childrenMovieType = childrenMovieType;
    }

    /**
     * @return movie type for a given movie code, if a new MovieType is created - it must be included in the list.
     */
    public Optional<MovieRentalType> getMovieRentalType(String movieCode) {
        List<MovieRentalType> movieRentalTypes = new ArrayList<>();
        movieRentalTypes.add(newMovieType);
        movieRentalTypes.add(regularMovieType);
        movieRentalTypes.add(childrenMovieType);
        return movieRentalTypes
                .stream()
                .filter(movieRentalType -> movieRentalType.code().equalsIgnoreCase(movieCode))
                .findFirst();
    }
}
