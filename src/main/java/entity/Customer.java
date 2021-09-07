package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Customer entity which keeps customer details
 */
@Data
@Builder
@AllArgsConstructor
public class Customer {

    private String name;
    private List<MovieRental> rentals;

}
