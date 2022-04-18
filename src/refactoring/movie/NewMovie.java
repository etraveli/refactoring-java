package refactoring.movie;

public class NewMovie extends Movie {

    public NewMovie(String title) {
        super(title);
    }

    @Override
    public double getRentalAmount(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int getFrequencyPoints(int daysRented) {
        if (daysRented > 2) {
            return 2;
        } else {
            return super.getFrequencyPoints(daysRented);
        }
    }
}
