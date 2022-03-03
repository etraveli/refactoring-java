import services.CustomerService;
import services.RentalInfoService;

public class Main {

  public static void main(String[] args) {
    CustomerService customerService = new CustomerService();
    RentalInfoService rentalInfoService = new RentalInfoService();

    String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";
    String result = rentalInfoService.statement(customerService.getCustomer());

    if (!result.equals(expected)) {
      throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
    }

    System.out.println("Success");
  }
}
