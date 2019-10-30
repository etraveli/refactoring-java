package receipt;

import customer.Customer;
import stock.Movies;
import stock.PriceRange;

public class Receipt {
    private Customer customer; // frequent points == size of movies
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
                    .append(movies.getMovieByCode(rental.getMovieId()).getPriceRange().getTotalCost(rental.getDays()));

            // add bonus point
            if (movies.getMovieByCode(rental.getMovieId()).getPriceRange() == PriceRange.NEW && rental.getDays() > 2) {
                customer.addFrequentEnterPoints(1);
            }

            customer.addFrequentEnterPoints(1);
        });

        stringBuilder.append("\nAmount owed is ")
                .append((Double) customer.getRentals().stream().map(
                        rental -> movies.getMovieByCode(rental.getMovieId())
                                .getPriceRange()
                                .getTotalCost(rental.getDays()))
                        .mapToDouble(Double::doubleValue).sum());

        stringBuilder.append("\nYou earned ").append(customer.getRentals().size()).append(" frequent points");

        String str = stringBuilder.toString();

        System.out.println(str);

        return str;
    }
}
