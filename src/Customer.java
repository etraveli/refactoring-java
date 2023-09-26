import java.util.List;
import java.util.UUID;

public class Customer {
	
	private String customerId;
    private String name;
    private List<MovieRental> rentals;

    public Customer(String name, List<MovieRental> rentals) {
    	this.customerId = assignId();
        this.name = name;
        this.rentals = rentals;
    }
    
    public String getCustomerId() {
    	return customerId;
    }

    public String getName() {
        return name;
    }

    public List<MovieRental> getRentals() {
        return rentals;
    }
    
    public int getFrequenterPoint() {
    	int frequenterPoint = 0;
    	for(MovieRental rental : rentals) {
    		frequenterPoint += 
    				rental.getMovie().getCategory().equals(Movie.Category.NEW)
    				&& rental.getDays() > 2 ? 
    				2 : 1;
    	}
    	return frequenterPoint;
    }
    
    public static Customer get(String customerId) {
    	//TODO implement a fetch when there is an use case
    	return new Customer(customerId, null);
    }
    
    private String assignId() {
    	return UUID.randomUUID().toString();
    }
}
