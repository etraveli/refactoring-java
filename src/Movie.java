public class Movie {
    private String title;
    private String code;
    private int days;

    public Movie(String title, String code) {

        this.title = title;
        this.code = code;
        days = 0;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public int getDays() { return days; }

    public Movie rentMovie(Integer days) {
        this.days = days;
        return this;
    }
}
