import com.etravli.payslip.model.PaySlip;
import com.etravli.payslip.service.PaySlipService;
import com.etravli.rent.domain.Customer;
import com.etravli.rent.domain.Movie;
import com.etravli.rent.domain.MovieRental;
import com.etravli.rent.enums.CodeEnum;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;


public class Main {

  public static final String EXPECTED_MESSAGE_NO_OF_LINES = "Expected %d line(s) in the pay slip but Got %d line(s)";
  public static final String EXPECTED_MESSAGE_TOTAL_AMOUNT = "Expected:%.1f as total amount but Got %.1f";
  public static final String EXPECTED_MESSAGE_TOTAL_EARNED_POINT = "Expected:%d as total earned points but Got %d";

  public static final String EXPECTED_MESSAGE_PAYMENT_SLIP_PRINT = "Expected:%s but Got %s";

  public static void main(String[] args){
    PaySlipService srv= new PaySlipService();
    Customer customerRent = createCustomerRent();
    PaySlip paySlip= srv.buildStatement(customerRent);

    // Validate payment slip structure
    if (paySlip.getDetails().size()!=2) {
      throw new AssertionError(String.format(EXPECTED_MESSAGE_NO_OF_LINES,2,paySlip.getDetails().size()));
    }
    if (!paySlip.getTotalAmount().equals(5.5D)) {
      throw new AssertionError(String.format(EXPECTED_MESSAGE_TOTAL_AMOUNT,5.5,paySlip.getTotalAmount()));
    }
    if (!paySlip.getTotalEarnedPoints().equals(2)) {
      throw new AssertionError(String.format(EXPECTED_MESSAGE_TOTAL_EARNED_POINT,2,paySlip.getTotalEarnedPoints()));
    }

    // Validate payment slip generation
    String expected=getExpectedPrint();
    String actualPrint=srv.printStatement(paySlip);
    if (!expected.equals(actualPrint)) {
      throw new AssertionError(String.format(EXPECTED_MESSAGE_PAYMENT_SLIP_PRINT,expected,actualPrint));
    }
    System.out.println("Success");
  }


  /**
   * Create sample of customer rent
   * @return customer
   */
  private static Customer createCustomerRent() {
    Customer customerRent= new Customer("C. U. Stomer");
    customerRent.addRent(new MovieRental(new Movie("F001","You've Got Mail", CodeEnum.REGULAR),3));
    customerRent.addRent(new MovieRental(new Movie("F002","Matrix", CodeEnum.REGULAR),1));
    return customerRent;
  }


  /**
   * Load expected payment slip
   * @return String
   */
  private static String getExpectedPrint() {
    URL resource = Main.class.getClassLoader().getResource("sample.txt");
    try {
      Path file = Paths.get(Objects.requireNonNull(resource).toURI());
      return Files.readString(file);
    } catch (URISyntaxException | IOException e) {
      throw new RuntimeException(e);
    }

  }


}
