package com.rentalmovies.movierental.repository;

import com.rentalmovies.common.GenericDaoImpl;
import com.rentalmovies.movierental.model.MovieRental;

public class MoveRentalDaoImpl extends GenericDaoImpl<MovieRental, Long> implements MovieRentalDao
{
    public MoveRentalDaoImpl()
    {
        super(MovieRental.class);
    }
}
