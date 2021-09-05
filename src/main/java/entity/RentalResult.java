package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RentalResult {

    private StringBuilder result;
    private double totalAmount;
    private int frequentEnterPoints;
}
