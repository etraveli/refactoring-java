public class Movie {
    private String title;
    private MovieCodeType code;

    public Movie(String title, MovieCodeType code) {

        this.title = title;
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public MovieCodeType getCode() {
        return code;
    }
}
