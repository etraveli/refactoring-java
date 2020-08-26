public class RentalInfo {

  public String getCustomerStatement(Customer customer) {

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    String result = String.format("Rental Record for %s (ID: %d)" + System.lineSeparator(), customer.getName(),
        customer.getId());
    for (MovieRental rented : customer.getCurrentRentalList()) {
      double thisAmount = 0;
      int rentedDays = rented.getRentalDays();
      switch (rented.getMovie().getTag()) {
        case REGULAR:
          thisAmount = rentedDays > 2 ? 2 + ((rentedDays - 2) * 1.5) : 2;
          break;
        case NEW:
          thisAmount = rentedDays * 3;
          // add bonus for a 2+ day new release rental
          if (rentedDays > 2)
            frequentEnterPoints += 1;
          break;
        case CHILDREN:
          thisAmount = rentedDays > 3 ? 3 + ((rentedDays - 3) * 1.5) : 1.5;
          break;
      }

      // add frequent bonus points
      frequentEnterPoints += 1;

      // print figures for this rental
      result += String.format("\t%s\t%s" + System.lineSeparator(), rented.getMovie().getTitle(),
          String.valueOf(thisAmount));
      totalAmount += thisAmount;
    }
    // add footer lines
    result += String.format(
        "Amount owed is %s" + System.lineSeparator() + "You earned %d frequent points" + System.lineSeparator(),
        String.valueOf(totalAmount), frequentEnterPoints);

    return result;
  }
}
