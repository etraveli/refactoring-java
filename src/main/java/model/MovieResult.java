package model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieResult {
  private String movieId;
  private String movieTitle;
  private double amount;
}
