package com.etraveli;

import com.etraveli.model.Customer;
import com.etraveli.model.MovieRental;
import com.etraveli.repository.CustomerRepository;
import com.etraveli.repository.InitialCustomerRepository;
import com.etraveli.repository.InitialMovieRepository;
import com.etraveli.repository.MovieRepository;

import java.util.Arrays;
public class Main {

  public static void main(String[] args) {
    String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";
    MovieRepository movieRepository = new InitialMovieRepository();
    CustomerRepository customerRepository = new InitialCustomerRepository();
    String result = new RentalInfo(movieRepository).statement(customerRepository.getCustomerById("C001"));

    if (!result.equals(expected)) {
      throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
    }

    System.out.println("Success");
  }
}
