import java.util.Objects;

public class Movie {
    private final String title;
    private final String code;

    public Movie(String title, String code) {
        Objects.requireNonNull(title, code);
        this.title = title;
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public double getRentalAmount(int daysRented) {
        switch (code) {
            case "regular":
                if (daysRented > 2) {
                    return ((daysRented - 2) * 1.5) + 2;
                } else {
                    return 2;
                }
            case "new":
                return daysRented * 3;
            case "childrens":
                if (daysRented > 3) {
                    return ((daysRented - 3) * 1.5) + 1.5;
                } else {
                    return 1.5;
                }
            default:
                return 0;
        }
    }

    public int getFrequencyPoints(int daysRented) {
        if (code.equals("new")  && daysRented > 2) {
            return 2;
        }
        return 1;
    }
}
