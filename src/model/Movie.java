package model;

public class Movie {

    private String id;
    private String title;
    private RentalCategory rentalCategory;

    public Movie(String id, String title, RentalCategory rentalCategory) {
        this.id = id;
        this.title = title;
        this.rentalCategory = rentalCategory;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public RentalCategory getRentalCategory() {
        return rentalCategory;
    }
}
