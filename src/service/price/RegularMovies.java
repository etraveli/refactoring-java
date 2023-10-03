package service.price;

public class RegularMovies implements CalculatePrice{
    private double baseAmount = 2;
    private double lateFeeAmt = 1.5;
    @Override
    public double calculate(int days) {
        return (days > 2) ? ((days - 2) * lateFeeAmt) +  baseAmount : baseAmount;
    }
}
