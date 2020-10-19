public class Movie {

    private final String id;
    private final String title;
    private String code;

    public Movie(String id, String title, String code) {
        this.id = id;
        this.title = title;
        this.code = code;
    }

    public String getId() { return id; }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }
}
