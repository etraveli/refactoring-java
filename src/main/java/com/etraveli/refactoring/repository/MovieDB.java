package com.etraveli.refactoring.repository;

import com.etraveli.refactoring.model.*;
import java.util.*;

public class MovieDB {
    private Map<String, Movie> movies;
    public MovieDB(){
        movies = new HashMap<String,Movie>();
        movies.put("F001", new Movie("You've Got Mail", MovieCode.REGULAR));
        movies.put("F002", new Movie("Matrix", MovieCode.REGULAR));
        movies.put("F003", new Movie("Cars", MovieCode.CHILDRENS));
        movies.put("F004", new Movie("Fast & Furious X", MovieCode.NEW));
    }

    public Movie get(String id){  
        return movies.get(id);
    }

}
