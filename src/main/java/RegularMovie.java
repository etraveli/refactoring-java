public class RegularMovie extends Movie {

    private static final double STANDARD_AMOUNT = 2;

    public RegularMovie(String title) {
        super(title);
    }

    @Override
    public double getRentalAmount(int daysRented) {
        if (daysRented > 2) {
            return ((daysRented - 2) * 1.5) + STANDARD_AMOUNT;
        } else {
            return STANDARD_AMOUNT;
        }
    }
}
