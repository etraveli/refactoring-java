package refactoring.java;

public class PriceCalculationResult {
    private double price;
    private int points;

    public PriceCalculationResult(double price, int points) {
        this.price = price;
        this.points = points;
    }

    public double getPrice() {
        return price;
    }

    public int getPoints() {
        return points;
    }
}
