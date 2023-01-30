import java.util.List;
/*
 * Added a field called frequentEnterPoints, as this property seems like something that pertains to the customer.
 * This needs to be modified every time a rental is done. 
 * In the constructor setting it as 0 as for a new customer normally it would be 0.
 */
public class Customer {
    private String name;
    private List<MovieRental> rentals;
    private int frequentEnterPoints;

    public Customer(String name, List<MovieRental> rentals) {
        this.name = name;
        this.rentals = rentals;
        this.frequentEnterPoints = 0;
    }
    /*
     * Overloading the constructor here in a special case where a new customer is to be added with existing frequentEnterPoints
     */
    public Customer(String name, List<MovieRental> rentals, int frequentEnterPoints) {
        this.name = name;
        this.rentals = rentals;
        this.frequentEnterPoints = frequentEnterPoints;
    }

    public String getName() {
        return name;
    }

    public List<MovieRental> getRentals() {
        return rentals;
    }
   public int getFrequentEnterPoints() {
       return frequentEnterPoints;
   }
   public void setFrequentEnterPoints(int frequentEnterPoints) {
       this.frequentEnterPoints = frequentEnterPoints;
   }
   public void addFrequentEnterPoints(int frequentEnterPoints) {
       this.frequentEnterPoints+=frequentEnterPoints;
   }
}
