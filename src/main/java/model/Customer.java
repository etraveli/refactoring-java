package model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
  private String name;
  private List<MovieRental> rentals;
}
