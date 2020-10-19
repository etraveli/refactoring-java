
public class Rental {
    private int days;
    private Movie movie;

    public Rental(Movie movie, int days) {
        this.movie = movie;
        this.days = days;
    }

    public double getAmount() throws Exception {
        double amount = 0;
        String code = movie.getCode();

        switch (code) {
            case "regular" -> {
                amount = 2;
                if (days > 2) {
                    amount = ((days - 2) * 1.5) + amount;
                }
            }
            case "new" -> {
                amount = days * 3;
            }
            case "childrens" -> {
                amount = 1.5;
                if (days > 3) {
                    amount = ((days - 3) * 1.5) + amount;
                }
            }
        }
        if (amount <= 0) {
            throw new Exception("Could not calculate amount due");
        }
        return amount;
    }

    public int getBonusPoints() throws NullPointerException {
        String code = movie.getCode();

        //add one frequent bonus point
        int bonusPoint = 1;

        // add bonus for a two day new release rental
        if (code.equals("new") && days > 2) {
            bonusPoint++;
        }
        return bonusPoint;
    }

    public String getMovieTitle() {
        return movie.getTitle();
    }

}