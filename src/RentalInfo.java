public class RentalInfo {

  public static String statement(Customer customer) {
    double totalAmount = 0;
    StringBuilder result = new StringBuilder("Rental Record for ").append(customer.getName()).append("\n");
    for (MovieRental rental : customer.getRentals()) {
      
      //print figures for this rental
      result.append("\t").append(rental.getMovie().getTitle())
      .append("\t").append(rental.getRentalAmount()).append("\n");
      totalAmount += rental.getRentalAmount();
    }
    // add footer lines
    result.append("Amount owed is ").append(totalAmount).append("\n")
    .append("You earned ").append(customer.getFrequenterPoint()).append(" frequent points\n");

    return result.toString();
  }
}
