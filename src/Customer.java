import java.util.List;

public class Customer {
    private final String name;
    private List<Rental> rentals;

    public Customer(String name, List<Rental> rentals) {
        this.name = name;
        this.rentals = rentals;
    }

    public String statement() {

        double totalAmount = 0;
        int totalBonus = 0;
        StringBuilder result = new StringBuilder("Rental Record for " + name + "\n");

        for (Rental r : rentals) {
            String title = r.getMovieTitle();
            double rentalAmount;
            int rentalBonus;

            try {
                rentalAmount = r.getAmount();
                rentalBonus = r.getBonusPoints();
            } catch (Exception ex) {
                return "Something went wrong: " + ex.getMessage();
            }

            //print figures for this rental
            result.append("\t")
                    .append(title).append("\t")
                    .append(rentalAmount).append("\n");

            totalBonus += rentalBonus;
            totalAmount += rentalAmount;
        }

        // add footer lines
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(totalBonus).append(" frequent points\n");

        return result.toString();
    }
}