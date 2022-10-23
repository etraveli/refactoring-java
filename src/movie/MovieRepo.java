package movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import movie.code.exceptions.MovieCodeInstantiationException;
import movie.code.exceptions.MovieCodeNotFoundException;

public class MovieRepo extends ArrayList<Movie> {

    public MovieRepo fetch() throws MovieCodeNotFoundException, MovieCodeInstantiationException {
        this.add(new Movie("F001", "You've Got Mail", "regular"));
        this.add(new Movie("F002", "Matrix", "regular"));
        this.add(new Movie("F003", "Cars", "childrens"));
        this.add(new Movie("F004", "Fast & Furious X", "new"));
        return this;
    }

    public HashMap<String, Movie> toHashMap() {
        return this.stream().collect(
                Collectors.toMap(Movie::getId, movie -> movie, (prev, next) -> next, HashMap::new));
    }
}
