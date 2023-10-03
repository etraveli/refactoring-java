package service.price;

public class NewMovies implements CalculatePrice{
    @Override
    public double calculate(int days) {
        return days * 3;
    }
}
