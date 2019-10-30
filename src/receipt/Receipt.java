package receipt;

import customer.Customer;
import stock.Movies;
import stock.PriceType;

public class Receipt {
    private Customer customer;
    private Movies movies;

    public Receipt(Customer customer) {
        this.customer = customer;
        this.movies = Movies.getInstance();
    }

    public String getReceipt() {
        StringBuilder stringBuilder = new StringBuilder("Rental Record for " + customer.getName());
        customer.getRentals().forEach(rental -> {
            stringBuilder.append("\n\t")
                    .append(movies.getMovieByCode(rental.getMovieId()).getTitle())
                    .append("\t")
                    .append(movies.getMovieByCode(rental.getMovieId()).getPriceType().getTotalCost(rental.getDays()));

            // add bonus point
            if (movies.getMovieByCode(rental.getMovieId()).getPriceType() == PriceType.NEW && rental.getDays() > 2) {
                customer.addFrequentEnterPoints(1);
            }

            customer.addFrequentEnterPoints(1);
        });

        stringBuilder.append("\nAmount owed is ")
                .append((Double) customer.getRentals().stream().map(
                        rental -> movies.getMovieByCode(rental.getMovieId())
                                .getPriceType()
                                .getTotalCost(rental.getDays()))
                        .mapToDouble(Double::doubleValue)
                        .sum()
                );

        stringBuilder.append("\nYou earned ").append(customer.getRentals().size()).append(" frequent points\n");

        return stringBuilder.toString();
    }
}
