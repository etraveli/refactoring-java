package stock;

public class Movie {
    private String title;
    private PriceType priceType;

    public Movie(String title, PriceType priceType) {
        this.title = title;
        this.priceType = priceType;
    }

    public String getTitle() {
        return title;
    }

    public PriceType getPriceType() {
        return priceType;
    }

}
