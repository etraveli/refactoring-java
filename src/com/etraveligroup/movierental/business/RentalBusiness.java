package com.etraveligroup.movierental.business;

import com.etraveligroup.movierental.models.Customer;

/**
 * @author LMOPURI
 *
 */
public interface RentalBusiness {

	String calculateMoviesRent(Customer customer) throws Exception;

	int calculateBonusPoints(Customer customer) throws Exception;

}
