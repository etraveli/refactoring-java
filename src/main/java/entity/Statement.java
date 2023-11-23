package entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Statement {

    private final String customerName;
    private final List<TitleAmount> rentals;
    private int frequentEnterPoints;

    public Statement(String customerName) {
        this.customerName = customerName;
        rentals = new ArrayList<>();
        frequentEnterPoints = 0;
    }

    public double getTotalAmount() {
        return rentals.stream()
                .map(r -> BigDecimal.valueOf(r.getAmount()))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .doubleValue();
    }

    public void addRental(String title, double amount) {
        rentals.add(new TitleAmount(title, amount));
    }

    public void incrementFrequentEnterPoints() {
        frequentEnterPoints++;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Rental Record for ").append(customerName).append("\n");
        rentals.forEach(r -> sb.append(r.toString()));
        sb.append("Amount owed is ").append(getTotalAmount()).append("\n");
        sb.append("You earned ").append(frequentEnterPoints).append(" frequent points").append("\n");

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Statement statement = (Statement) o;
        return customerName.equals(statement.customerName)
                && Objects.equals(rentals, statement.rentals)
                && getTotalAmount() == statement.getTotalAmount()
                && frequentEnterPoints == statement.frequentEnterPoints;
    }
}

