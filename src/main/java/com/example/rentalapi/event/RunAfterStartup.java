package com.example.rentalapi.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.rentalapi.service.MovieService;

@Component
public class RunAfterStartup {

	@Autowired
	MovieService movieService;

	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
		movieService.intializeDefaultMovies();
	}

}