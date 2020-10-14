import java.util.List;

public class Customer {
    private String name;
    private List<Movie> rentals;

    public Customer(String name, List<Movie> rentals) {
        this.name = name;
        this.rentals = rentals;
    }

    public String statement() {

        double totalAmount = 0;
        int frequentEnterPoints = 0;
        String result = "Rental Record for " + this.name + "\n";
        for (Movie r : this.rentals) {
            double thisAmount = determineAmount(r);

            //add frequent bonus points
            // add bonus for a two day new release rental
            frequentEnterPoints += r.getCode() == "new" && r.getDays() > 2 ? 2 : 1;

            //print figures for this rental
            result += "\t" + r.getTitle() + "\t" + thisAmount + "\n";
            totalAmount = totalAmount + thisAmount;
        }
        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentEnterPoints + " frequent points\n";

        return result;
    }

    private double determineAmount(Movie r) {
        double thisAmount = 2;
        // determine amount for each movie
        if (r.getCode().equals("regular")) {
            return r.getDays() > 2
                    ? thisAmount + calculateAmount (r.getDays(), 2, 1.5)
                    : thisAmount;
        }
        if (r.getCode().equals("new")) {
            return calculateAmount (r.getDays(), 0, 1.5);
        }

        thisAmount = 1.5;
        return r.getDays() > 3
                ? thisAmount + calculateAmount (r.getDays(), 3, 1.5)
                : thisAmount;
    }

    private double calculateAmount(int days, double subtractionFactor, double multiplicationFactor) {
        return ((days - subtractionFactor) * multiplicationFactor);
    }
}
