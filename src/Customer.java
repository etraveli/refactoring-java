import java.util.List;

public class Customer {
    // created "nextId" to automatically increment the Customer id at creation time.
    // created "id" to identify a given Customer uniquely.
    private static int nextId = 1;
    private int id;
    private String name;
    private List<MovieRental> currentRentalList;

    public Customer(String name, List<MovieRental> rentals) {
        this.id = nextId;
        this.name = name;
        this.currentRentalList = rentals;
        this.nextId++;
    }

    // used at object creation time
    public static int getNextId() {
        return nextId;
    }

    // no setter as it is automatically incremented
    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // now able to reset the current rental list
    public void setRentalList(List<MovieRental> newRentalList) {
        this.currentRentalList = newRentalList;
    }

    // adding new rented movies into the existing list of the customer
    public void addNewRentals(List<MovieRental> extraRentalList) {
        this.currentRentalList.addAll(extraRentalList);
    }

    public List<MovieRental> getCurrentRentalList() {
        return currentRentalList;
    }
}
