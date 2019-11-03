package movie.dto;

import java.util.Map;

public class RentalStatement {

    private double totalAmount;
    private int frequentPoints;
    private Map<String, Double> moviesAmounts;

    public RentalStatement(double totalAmount, int frequentPoints, Map<String, Double> moviesAmounts) {
        this.totalAmount = totalAmount;
        this.frequentPoints = frequentPoints;
        this.moviesAmounts = moviesAmounts;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public int getFrequentPoints() {
        return frequentPoints;
    }

    public Map<String, Double> getMoviesAmounts() {
        return moviesAmounts;
    }
}
