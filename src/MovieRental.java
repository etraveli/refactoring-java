public class MovieRental {
    private Movie movie;
    private int days;

    public MovieRental(String movieId, int days) {
        this.movie = Movie.get(movieId);
        this.days = days;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDays() {
        return days;
    }
    
    public double getRentalAmount() {
    	switch(movie.getCategory()) {
	    	case REGULAR: return 2 + Math.max(0, days - 2) * 1.5;
	    	case NEW: return days * 3;
	    	case CHILDRENS: return 1.5 + Math.max(0, days - 3) * 1.5;
    	}
    	return 0.0;
    }
}
