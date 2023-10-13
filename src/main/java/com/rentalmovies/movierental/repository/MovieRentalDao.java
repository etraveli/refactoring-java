package com.rentalmovies.movierental.repository;

import com.rentalmovies.common.GenericDao;
import com.rentalmovies.movierental.model.MovieRental;

/**
 * The MovieRentalDao interface provides an abstraction layer for accessing
 * and manipulating MoveRental entities in the database. It extends the GenericDao
 * interface to inherit common database operation methods and can be extended
 * to include Customer specific database operations.
 *
 * <p>This additional layer of abstraction is beneficial for separating the
 * concerns, making the codebase more modular, and facilitating easier testing
 * by allowing the use of mock implementations or stubs.</p>
 *
 * @author Sajid Riaz
 * @see GenericDao
 */
public interface MovieRentalDao extends GenericDao<MovieRental, Long>
{
    // Entity specific functions
}
