import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Statement {

    private final String customerName;
    private final List<TitlePoints> rentals;
    private double totalPoints;
    private int frequentEnterPoints;

    public Statement(String customerName) {
        this.customerName = customerName;
        rentals = new ArrayList<>();
        totalPoints = 0.0;
        frequentEnterPoints = 0;
    }

    public void addRental(String title, double points) {
        rentals.add(new TitlePoints(title, points));
    }

    public void addFooter(double totalPoints, int frequentEnterPoints) {
        this.totalPoints = totalPoints;
        this.frequentEnterPoints = frequentEnterPoints;
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
        sb.append("Amount owed is ").append(totalPoints);
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
                && totalPoints == statement.totalPoints
                && frequentEnterPoints == statement.frequentEnterPoints;
    }

    static class TitlePoints {
        private final String title;
        private final double points;

        public TitlePoints(String title, double points) {
            this.title = title;
            this.points = points;
        }

        @Override
        public String toString() {
            return "\t" + title + "\t" + points + "\n";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TitlePoints tp = (TitlePoints) o;
            return title.equals(tp.title) && points == tp.points;
        }
    }
}