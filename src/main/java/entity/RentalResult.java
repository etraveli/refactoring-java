package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * RentalResult entity which keeps Rental Result details
 */
@Data
@Builder
@AllArgsConstructor
public class RentalResult {

    private double totalAmount;
    private int frequentEnterPoints;
    private HashMap<String, Movie> movieMap;

}
