package com.etraveli.movie.rental.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MovieRentalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieRentalServiceApplication.class, args);
	}

}
