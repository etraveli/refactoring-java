package io.refactoring;

import io.refactoring.model.Customer;
import io.refactoring.model.MovieRental;
import io.refactoring.service.IRentalInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Main {

  public static void main(String[] args) {
    var configurableApplicationContext = SpringApplication.run(Main.class, args);
    String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";
    String result = ((IRentalInfo)configurableApplicationContext.getBean("rentalInfo")).statement(new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));

    if (!result.equals(expected)) {
      throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
    }

    System.out.println("Success");
  }

}