package serviceImpl;

import entity.MovieRental;
import lombok.AllArgsConstructor;
import service.Receipt;
import constant.RentalStatementConstants;

import java.util.List;


@AllArgsConstructor
public class ReceiptString extends Receipt<String> {

    @Override
    public String generate() {
        if (getReceiptDetail().getCustomer() == null) {
            return RentalStatementConstants.PLEASE_SELECT_CUSTOMER;
        }

        if (getReceiptDetail().getCustomer().getRentals() == null || getReceiptDetail().getCustomer().getRentals().isEmpty()) {
            return RentalStatementConstants.RENTAL_RECORD_FOR + getReceiptDetail().getCustomer().getName() + "\n" + RentalStatementConstants.IS_ZERO;
        }

        StringBuilder result = new StringBuilder();
        result.append(RentalStatementConstants.RENTAL_RECORD_FOR).append(getReceiptDetail().getCustomer().getName()).append("\n");
        double currentRentAmount;
        List<MovieRental> rentalList = getReceiptDetail().getCustomer().getRentals();

        for (MovieRental rental : rentalList) {
            currentRentAmount = rental.getRentAmount();

            result.append("\t")
                    .append(rental.getMovie().getTitle())
                    .append("\t")
                    .append(currentRentAmount)
                    .append("\n");

        }

        result.append(RentalStatementConstants.AMOUNT_OWED_IS).append(getReceiptDetail().getTotalAmount()).append("\n");
        result.append(RentalStatementConstants.YOU_EARNED).append(getReceiptDetail().getFrequentEnterPoints()).append(RentalStatementConstants.FREQUENT_POINTS).append("\n");

        return result.toString();
    }
}
