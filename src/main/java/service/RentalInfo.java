package service;

import entity.Customer;
import entity.MovieRental;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public abstract class RentalInfo<T> {

    private Customer customer;
    private Receipt<T> receipt;

    abstract public T statement();

    public int getTotalFrequentEnterPoints() {
        if (isCustomerRentalNotValid())
            return 0;
        List<MovieRental> rentalList = customer.getRentals();
        int frequentEnterPoints = rentalList.stream()
                .mapToInt(MovieRental::getFrequentEnterPoints)
                .sum();
        return frequentEnterPoints;
    }

    public double getTotalRentalAmount() {
        if (isCustomerRentalNotValid())
            return 0;
        List<MovieRental> rentalList = customer.getRentals();
        double totalAmount = rentalList.stream()
                .mapToDouble(MovieRental::getRentAmount)
                .sum();
        return totalAmount;
    }

    private boolean isCustomerRentalNotValid() {
        if (customer == null)
            return true;

        List<MovieRental> rentalList = customer.getRentals();

        return rentalList == null || rentalList.isEmpty();

    }

    public Receipt<T> getReceipt() {
        return receipt;
    }

    public Customer getCustomer() {
        return customer;
    }

}
