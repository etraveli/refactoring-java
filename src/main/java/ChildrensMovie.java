public class ChildrensMovie extends Movie {

    private static final double STANDARD_AMOUNT = 1.5;

    public ChildrensMovie(String title) {
        super(title);
    }

    @Override
    public double getRentalAmount(int daysRented) {
        if (daysRented > 3) {
            return ((daysRented - 3) * 1.5) + STANDARD_AMOUNT;
        } else {
            return STANDARD_AMOUNT;
        }
    }
}
