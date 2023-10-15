package com.rentalmovies.movie.repository;

import com.rentalmovies.common.GenericDaoImpl;
import com.rentalmovies.movie.model.Movie;

public class MovieDaoImpl extends GenericDaoImpl<Movie, String> implements MovieDao
{
    public MovieDaoImpl()
    {
        super(Movie.class);
    }
}
