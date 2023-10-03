package service.price;

public class ChildrenMovies implements CalculatePrice {

    private double baseAmount = 1.5;
    @Override
    public double calculate(int days) {
        return (days > 3) ? ((days - 3) * baseAmount) +  baseAmount : baseAmount;
    }
}
