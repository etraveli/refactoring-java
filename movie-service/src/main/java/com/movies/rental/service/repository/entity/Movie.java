package com.movies.rental.service.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("movie")
@Getter
@AllArgsConstructor
public class Movie {

  @Id private final String movieId;

  private final String title;

  private MovieType genre;
}
