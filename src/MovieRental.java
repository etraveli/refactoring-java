public class MovieRental {
    private String movieId;
    private int days;

    public MovieRental(String movieId, int days) {
        this.movieId = movieId;
        this.days = days;
    }

    public String getMovieId() {
        return movieId;
    }

    public int getDays() {
        return days;
    }

    public double getAmount(String movieCode) throws Exception {
        double amount = 0;

        if (movieCode.equals("regular")) {
            amount = 2;
            if (days > 2) {
                amount = ((days - 2) * 1.5) + amount;
            }
        }
        else if (movieCode.equals("new")) {
            amount = days * 3;
        }
        else if (movieCode.equals("childrens")) {
            amount = 1.5;
            if (days > 3) {
                amount = ((days - 3) * 1.5) + amount;
            }
        }
        if (amount == 0) {
            throw new Exception("Could not calculate amount due");
        }
        return amount;
    }

    public int getBonusPoints(String movieCode) {
        //add one frequent bonus point
        int bonusPoint = 1;

        // add bonus for a two day new release rental
        if (movieCode.equals("new") && days > 2) {
            bonusPoint++;
        }
        return bonusPoint;
    }
}
