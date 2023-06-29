package com.movies.rental.service.repository.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Customer {

  @Id private String customerId;
  private String name;
  private List<MovieRental> movieRentals;
  private int bonusPoints;
  private double totalAmount;
}
