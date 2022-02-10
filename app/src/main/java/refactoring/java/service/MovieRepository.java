package refactoring.java.service;

import refactoring.java.model.Movie;

/**
 * Provides service to find a movie in the "database".
 */
public interface MovieRepository {
    Movie findById(String id);
}
