package model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieRental {
  private String movieId;
  private int days;
}
