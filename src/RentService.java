import customer.Customer;
import receipt.Receipt;

public class RentService {

    public String rent(Customer customer) {
        Receipt receipt = new Receipt(customer);
        return receipt.getReceipt();
    }
}
