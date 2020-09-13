
/**
 * 
 * The main idea behind most of my refactor is scalability. I refactored the
 * code, so that in case it gets much bigger, it will remain organized and easy
 * to read and maintain. Also, I am trying to follow the principle of Separation
 * of Logic as much as I can.
 * 
 */
public class Main {

  public static void main(String[] args) {
    System.out.println("############ Starting Test #############");
    // Covering more cases in testing
    testOriginal();
    test_CHILDREN_NEW();
    testEmptyRentals();
    testNullCustomer();

    System.out.println("#### All test cases passed successfully ###");
  }

  // The test case in original code
  // Testing calculation for ‘regular’ movies
  public static void testOriginal() {
    String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

    DataAccess dataAccess = new DataAccess();
    Customer customer = dataAccess.getCustomerById("C001");
    Statement statement = RentalInfo.generateStatement(customer);
    String result = statement.toString();

    assertIsEqual(expected, result);
  }

  // Testing calculation for ‘children’ and ‘new’
  public static void test_CHILDREN_NEW() {
    String expected = "Rental Record for Adam K\n\tCars\t4.5\n\tFast & Furious X\t12.0\nAmount owed is 16.5\nYou earned 3 frequent points\n";

    DataAccess dataAccess = new DataAccess();
    Customer customer = dataAccess.getCustomerById("C002");
    Statement statement = RentalInfo.generateStatement(customer);
    String result = statement.toString();

    assertIsEqual(expected, result);
  }

  public static void testEmptyRentals() {
    String expected = Const.Msg.NO_RESULT;

    DataAccess dataAccess = new DataAccess();
    Customer customer = dataAccess.getCustomerById("C004");
    Statement statement = RentalInfo.generateStatement(customer);
    String result = statement.toString();

    assertIsEqual(expected, result);
  }

  public static void testNullCustomer() {
    String expected = Const.Msg.NO_RESULT;

    DataAccess dataAccess = new DataAccess();
    Customer customer = dataAccess.getCustomerById("C009");
    Statement statement = RentalInfo.generateStatement(customer);
    String result = statement.toString();

    assertIsEqual(expected, result);
  }

  public static void assertIsEqual(String expected, String result) {
    if (!result.equals(expected)) {
      throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator()
          + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
    }

  }
}
