package com.movies.rental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.movies.rental")
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
