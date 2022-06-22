package se.etraveli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
public class CustomerDto {
    private String name;
    private List<MovieRental> rentals;
}
