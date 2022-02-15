package com.etraveligroup.movierental.models;

import com.etraveligroup.movierental.enums.MovieType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author LMOPURI
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

	private String id;
	private String title;
	private MovieType code;
	@Getter
	@Setter
	private static double rentalPrice;

}
