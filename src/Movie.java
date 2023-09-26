public class Movie {
	private String movieId;
    private String title;
    private Category category;

    public static Movie get(String movieId) {
    	return MovieStore.getInstance().getMovie(movieId);
    }
    
    public String getMovieId() {
    	return movieId;
    }

    public String getTitle() {
        return title;
    }

    public Category getCategory() {
        return category;
    }
    
    public Movie(String movieId, String title, Category category) {
    	this.movieId = movieId;
        this.title = title;
        this.category = category;
    }
    
    public static enum Category {
    	REGULAR, NEW, CHILDRENS;
    }
}
