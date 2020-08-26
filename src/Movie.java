public class Movie {

    // created "nextId" to automatically increment the movie id at creation time.
    // created "id" to identify a given movie uniquely.
    // introduced "tag" instead of "code" to avoid interpretation issues.
    private static int nextId = 1;
    private int id;
    private String title;
    private MovieTag tag;

    /*
     * "MovieTag" includes catching words intended for the movies. Formerly known as
     * "code".
     */
    public enum MovieTag {
        NEW, CHILDREN, REGULAR
    }

    public Movie(String title, MovieTag tag) {
        this.id = nextId;
        this.title = title;
        this.tag = tag;
        this.nextId++;
    }

    // used at object creation time
    public static int getNextId() {
        return nextId;
    }

    // no setter as it is automatically incremented
    public int getId() {
        return id;
    }

    // in case the title needs to be changed/updated
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    // in case the movie tag needs tbe changed/updated
    public void setTag(MovieTag tag) {
        this.tag = tag;
    }

    public MovieTag getTag() {
        return tag;
    }
}
