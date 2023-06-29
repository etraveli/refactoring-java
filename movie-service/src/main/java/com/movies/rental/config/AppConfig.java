package com.movies.rental.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan(basePackages = {"com.movies.rental"})
@EnableMongoRepositories(basePackages = {"com.movies.rental.service.repository"})
public class AppConfig {

}
