package com.etraveligroup.movierental.serviceimpl;

import com.etraveligroup.movierental.business.RentalBusiness;
import com.etraveligroup.movierental.businessimpl.RentalBusinessImpl;
import com.etraveligroup.movierental.models.Customer;
import com.etraveligroup.movierental.service.RentalManagerService;

import lombok.extern.log4j.Log4j;

/**
 * @author LMOPURI
 *
 */
@Log4j
public class RentalManagerServiceImpl implements RentalManagerService {

	public String getRentalAmoutAndBonusPoints(Customer customer) {
		log.info("Entered getRentalAmoutAndBonusPoints method");
		RentalBusiness rentalBusiness = new RentalBusinessImpl();
		StringBuilder output = new StringBuilder();
		try {
			output.append(rentalBusiness.calculateMoviesRent(customer));
			output.append("You earned " + rentalBusiness.calculateBonusPoints(customer) + " frequent points\n");
		} catch (Exception e) {
			output.append("Exception occured");
			log.error("Exception occured:", e);

		}

		log.info("Exit getRentalAmoutAndBonusPoints method");
		return output.toString();
	}
}
