import customer.Customer;
import receipt.Receipt;

public class RentMovie {

    public String rentMovies(Customer customer) {
        Receipt receipt = new Receipt(customer);
        return receipt.getReceipt();
    }
}
