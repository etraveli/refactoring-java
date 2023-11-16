package serviceImpl;

import entity.Customer;
import entity.ReceiptDetail;
import org.junit.jupiter.api.Test;
import service.Receipt;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RentalInfoStringTest {
    @Test
    void testStatement() {
        // Create mock objects
        Customer mockCustomer = mock(Customer.class);
        Receipt mockReceipt = mock(Receipt.class);
        ReceiptDetail mockReceiptDetail = mock(ReceiptDetail.class);

        // Set up the expected behavior for the mock objects
        when(mockCustomer.getName()).thenReturn("John Doe");
        when(mockReceipt.getReceiptDetail()).thenReturn(mockReceiptDetail);
        when(mockReceiptDetail.getTotalAmount()).thenReturn(30.0);
        when(mockReceiptDetail.getFrequentEnterPoints()).thenReturn(5);
        when(mockReceipt.generate()).thenReturn("Mocked result");


        // Create an instance of RentalInfoString with the mock objects
        RentalInfoString rentalInfoString = new RentalInfoString(mockCustomer, mockReceipt);

        // Call the method to be tested
        String result = rentalInfoString.statement();

        // Perform assertions based on the expected behavior
        String expected = "Mocked result";

        assertEquals(expected, result);
    }

}