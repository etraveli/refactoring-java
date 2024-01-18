package com.etraveli.model;

/**
 * @param movieId textual movie identifier
 * @param days    number of days for the rental
 */
public record MovieRental(String movieId, int days) {
}
