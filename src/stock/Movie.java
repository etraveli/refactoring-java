package stock;

public class Movie {
    private String title;
    private PriceRange priceRange;

    public Movie(String title, PriceRange priceRange) {
        this.title = title;
        this.priceRange = priceRange;
    }

    public String getTitle() {
        return title;
    }

    public PriceRange getPriceRange() {
        return priceRange;
    }

}
