package com.mithwick93.refactoring.java;

/**
 * MovieRental class to represent a movie rental
 *
 * @param movieId movie id
 * @param days    number of days the movie is rented for
 */
public record MovieRental(String movieId, int days) {
}
