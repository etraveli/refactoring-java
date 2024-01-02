package com.etraveli.repository;

import com.etraveli.modal.request.Movie;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {

    HashMap<String, Movie> moviesList = new HashMap();

    public MovieRepository() {
        moviesList.put("F001", new Movie("You've Got Mail", "regular"));
        moviesList.put("F002", new Movie("Matrix", "regular"));
        moviesList.put("F003", new Movie("Cars", "childrens"));
        moviesList.put("F004", new Movie("Fast & Furious X", "new"));
    }


    public List<Movie> getMoviesByIdList(List<String> movieIds) {
        return movieIds.stream()
                .filter(moviesList::containsKey)
                .map(moviesList::get)
                .collect(Collectors.toList());
    }
}
