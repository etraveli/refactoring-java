package serviceImpl;

import entity.Customer;
import entity.ReceiptDetail;
import service.Receipt;
import service.RentalInfo;

public class RentalInfoString extends RentalInfo<String> {
    public RentalInfoString(Customer customer, Receipt<String> receipt) {
        super(customer, receipt);
    }

    public String statement() {
        ReceiptDetail receiptDetail = new ReceiptDetail(getTotalFrequentEnterPoints(), getTotalRentalAmount(), getCustomer());
        getReceipt().setReceiptDetail(receiptDetail);
        return getReceipt().generate();
    }
}
