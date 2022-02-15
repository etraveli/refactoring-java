package com.etraveligroup.movierental.businessimpl;

import com.etraveligroup.movierental.business.RentalBusiness;
import com.etraveligroup.movierental.dao.MoviesDao;
import com.etraveligroup.movierental.daoimpl.MoviesDaoImpl;
import com.etraveligroup.movierental.enums.MovieType;
import com.etraveligroup.movierental.models.Customer;
import com.etraveligroup.movierental.models.MovieRental;

import lombok.extern.log4j.Log4j;

/**
 * @author LMOPURI
 *
 */
@Log4j
public class RentalBusinessImpl implements RentalBusiness {
	MoviesDao moviesDao = new MoviesDaoImpl();

	public String getRentalAmoutAndBonusPoints(Customer customer) {
		log.info("Entered getRentalAmoutAndBonusPoints method");
		double totalAmount = 0;
		int frequentEnterPoints = 0;
		String result = "Rental Record for " + customer.getName() + "\n";
		for (MovieRental r : customer.getRentals()) {
			double thisAmount = 0;

			// determine amount for each movie
			if (moviesDao.getMovieDetails(r.getMovieId()).getCode().equals(MovieType.REGULAR)) {
				thisAmount = 2;// moviesDao.getAllMovieRentDetails(MovieType.REGULAR).get;
				if (r.getDays() > 2) {
					thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
				}
			}
			if (moviesDao.getMovieDetails(r.getMovieId()).getCode().equals(MovieType.NEW)) {
				thisAmount = r.getDays() * 3;
			}
			if (moviesDao.getMovieDetails(r.getMovieId()).getCode().equals(MovieType.CHILDRENS)) {
				thisAmount = 1.5;
				if (r.getDays() > 3) {
					thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
				}
			}

			// add frequent bonus points
			// frequentEnterPoints++;
			// add bonus for a two day new release rental
			if (moviesDao.getMovieDetails(r.getMovieId()).getCode().equals(MovieType.NEW) && r.getDays() > 2)
				frequentEnterPoints++;

			// print figures for this rental
			result += "\t" + moviesDao.getMovieDetails(r.getMovieId()).getTitle() + "\t" + thisAmount + "\n";
			totalAmount = totalAmount + thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentEnterPoints + " frequent points\n";
		log.info("Exit getRentalAmoutAndBonusPoints method");
		return result;
	}

	@Override
	public String calculateMoviesRent(Customer customer) throws Exception {
		log.info("Entered calculateMoviesRent method");
		double totalAmount = 0;
		StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");
		for (MovieRental r : customer.getRentals()) {
			double thisAmount = 0;

			thisAmount = rentCalc(r, thisAmount);

			// print figures for this rental
			result.append("\t" + moviesDao.getMovieDetails(r.getMovieId()).getTitle() + "\t" + thisAmount + "\n");

			totalAmount = totalAmount + thisAmount;
		}
		// add footer lines
		result.append("Amount owed is " + totalAmount + "\n");
		log.info("Exit calculateMoviesRent method");
		return result.toString();
	}

	private double rentCalc(MovieRental r, double thisAmount) throws Exception {
		log.info("Entered rentCalc method");
		// determine amount for each movie
		switch (moviesDao.getMovieDetails(r.getMovieId()).getCode().name()) {
		case "REGULAR":
			thisAmount = 2;
			if (r.getDays() > 2)
				thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
			break;
		case "NEW":
			thisAmount = r.getDays() * 3;
			break;
		case "CHILDRENS":
			thisAmount = 1.5;
			if (r.getDays() > 3)
				thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
			break;
		default:
			break;
		}
		log.info("Exit rentCalc method");
		return thisAmount;
	}

	@Override
	public int calculateBonusPoints(Customer customer) throws Exception {
		log.info("Entered calculateBonusPoints method");
		int frequentEnterPoints = 0;
		MoviesDao moviesDao = new MoviesDaoImpl();

		for (MovieRental r : customer.getRentals())
			if (moviesDao.getMovieDetails(r.getMovieId()).getCode().equals(MovieType.NEW) && r.getDays() > 2)
				frequentEnterPoints++;
		log.info("Exit calculateBonusPoints method");
		return frequentEnterPoints;
	}
}
