package com.movie.rental.model;

public class MovieRental {

	private String movieId;
	private int days;

	public MovieRental() {
		super();
	}

	public MovieRental(String movieId, int days) {
		super();
		this.movieId = movieId;
		this.days = days;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	@Override
	public String toString() {
		return "MovieRental [movieId=" + movieId + ", days=" + days + "]";
	}
}
