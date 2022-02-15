package com.etraveligroup.movierental.models;

import java.util.List;

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
public class Customer {
	private String name;
	private List<MovieRental> rentals;
}
