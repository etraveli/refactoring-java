package movie.service;

import movie.dto.RentalStatement;

public class StatementOutputService {

    public static String createStringStatement(RentalStatement rentalStatement, String customerName){
        StringBuilder result = new StringBuilder("Rental Record for " + customerName + "\n");

        rentalStatement.getMoviesAmounts().forEach(
                (movieId, amount) -> result.append("\t").append(movieId).append("\t").append(amount).append("\n"));

        // add footer lines
        result.append("Amount owed is ").append(rentalStatement.getTotalAmount()).append("\n");
        result.append("You earned ").append(rentalStatement.getFrequentPoints()).append(" frequent points\n");
        return String.valueOf(result);
    }
}
