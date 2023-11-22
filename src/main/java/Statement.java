import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Statement {

    private final String customerName;
    private final List<TitlePoints> rentals;
    private int frequentEnterPoints;

    public Statement(String customerName) {
        this.customerName = customerName;
        rentals = new ArrayList<>();
        frequentEnterPoints = 0;
    }

    public double getTotalPoints() {
        return rentals.stream().map( r -> r.amount)
                .reduce(0.0, Double::sum);
    }

    public void addRental(String title, double amount) {
        rentals.add(new TitlePoints(title, amount));
    }

    public void incrementFrequentEnterPoints(){
        frequentEnterPoints++;
    }

    @Override
    public String toString() {
        /*
        Rental Record for C. U. Stomer
        You've Got Mail	3.5
        Matrix	2.0
        Amount owed is 5.5
        You earned 2 frequent points
        */

        StringBuffer sb = new StringBuffer();
        sb.append("Rental Record for ").append(customerName);
        sb.append("\n");
        rentals.forEach(r -> sb.append(r.toString()));
        sb.append("Amount owed is ").append(getTotalPoints());
        sb.append("\n");
        sb.append("You earned ").append(frequentEnterPoints).append(" frequent points");
        sb.append("\n");

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Statement statement = (Statement) o;
        return customerName.equals(statement.customerName)
                && Objects.equals(rentals, statement.rentals)
                && getTotalPoints() == statement.getTotalPoints()
                && frequentEnterPoints == statement.frequentEnterPoints;
    }

    static class TitlePoints {
        private final String title;
        private final double amount;

        public TitlePoints(String title, double points) {
            this.title = title;
            this.amount = points;
        }

        @Override
        public String toString() {
            return "\t" + title + "\t" + amount + "\n";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TitlePoints tp = (TitlePoints) o;
            return title.equals(tp.title) && amount == tp.amount;
        }
    }
}