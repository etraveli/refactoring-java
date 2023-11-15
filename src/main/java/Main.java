import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    List<MovieRental> MovieRentalList= Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1));
    Customer customer= new Customer("C. U. Stomer",MovieRentalList) ;
    String result = new RentalInfo().statement(customer);
  }
}
