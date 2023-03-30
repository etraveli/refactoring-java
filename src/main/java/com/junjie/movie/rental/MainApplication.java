package com.junjie.movie.rental;

import com.junjie.movie.rental.repository.CustomerRepository;
import com.junjie.movie.rental.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		movieRepository.save(new Movie("You've Got Mail", MovieType.REGULAR));
//		movieRepository.save(new Movie("Matrix", MovieType.REGULAR));
//		movieRepository.save(new Movie("Cars", MovieType.CHILDREN));
//		movieRepository.save(new Movie("Fast & Furious X", MovieType.NEW));
//
//		customerRepository.save(new Customer("Junjie Feng"));
	}
}
