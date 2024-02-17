package com.example.rentalapi.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.example.rentalapi.constants.RentalConstants;

import jakarta.validation.constraints.NotNull;

public class Movie {
	@NotNull
    private String title;
	@NotNull
    private String code;

    private static final Set<String> VALID_CODES = new HashSet<>(Arrays.asList(RentalConstants.MOVIE_CODE_REGULAR, RentalConstants.MOVIE_CODE_NEW,  RentalConstants.MOVIE_CODE_CHILDRENS));
    
	public Movie(String title, String code) {
		super();
		this.title = title;
		this.code =code;
	}
	
	public String getTitle() {
		return title;
	}

	public String getCode() {
		return code;
	}
	public static boolean isValidCode(String code) {
        return VALID_CODES.contains(code);
    }
   
}
