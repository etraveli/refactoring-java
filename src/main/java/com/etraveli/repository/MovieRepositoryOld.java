package com.etraveli.repository;

import com.etraveli.modal.request.MovieRequest;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MovieRepositoryOld {

    HashMap<String, MovieRequest> moviesList = new HashMap();

    public MovieRepositoryOld() {
        /*moviesList.put("F001", new MovieRequest("You've Got Mail", "regular"));
        moviesList.put("F002", new MovieRequest("Matrix", "regular"));
        moviesList.put("F003", new MovieRequest("Cars", "childrens"));
        moviesList.put("F004", new MovieRequest("Fast & Furious X", "new"));*/
    }


    public List<MovieRequest> getMoviesByIdList(List<String> movieIds) {
        return movieIds.stream()
                .filter(moviesList::containsKey)
                .map(moviesList::get)
                .collect(Collectors.toList());
    }
}
