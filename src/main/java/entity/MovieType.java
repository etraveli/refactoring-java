package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import service.Price;
import type.ShowType;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieType {
    private Long MovieTypeID;
    private ShowType type;
    private Price price;
    public double getRentAmount(int rentalDays) {
        return price.getRentAmount(rentalDays);
    }
    public int getFrequentEnterPoints(int rentalDays) {
        return price.getFrequentRentedPoints(rentalDays);
    }
}
