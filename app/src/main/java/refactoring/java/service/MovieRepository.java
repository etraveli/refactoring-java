package refactoring.java.service;

import refactoring.java.model.Movie;

public interface MovieRepository {
    Movie findById(String id);
}
