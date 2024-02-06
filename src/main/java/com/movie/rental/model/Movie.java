package com.movie.rental.model;

public class Movie {
	
	private String title;
	private String code;
	
	public Movie() {
		super();
	}
	public Movie(String title, String code) {
		super();
		this.title = title;
		this.code = code;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return "Movie [title=" + title + ", code=" + code + "]";
	}	
}
