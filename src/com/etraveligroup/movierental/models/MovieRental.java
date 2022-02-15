package com.etraveligroup.movierental.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LMOPURI
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRental {
	private String movieId;
	private int days;
}
