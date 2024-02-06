package com.movie.rental.customexception;

@SuppressWarnings("serial")
public class InvalidMovieIdException extends RuntimeException {
	public InvalidMovieIdException() {
	}

	public InvalidMovieIdException(String msg) {
		super(msg);
	}
}