package customer;

import java.util.List;

public class Customer {
    private String name;
    private List<MovieRental> rentals;
    private int frequentEnterPoints;

    public Customer(String name, List<MovieRental> rentals) {
        this.name = name;
        this.rentals = rentals;
        this.frequentEnterPoints = 0;
    }

    public String getName() {
        return name;
    }

    public List<MovieRental> getRentals() {
        return rentals;
    }

    public void addFrequentEnterPoints(int points) {
        frequentEnterPoints += points;
    }

    public int getFrequentEnterPoints() {
        return frequentEnterPoints;
    };
}
