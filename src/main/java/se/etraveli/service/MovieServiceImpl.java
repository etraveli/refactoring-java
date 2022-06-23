package se.etraveli.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.etraveli.entity.Movie;
import se.etraveli.exception.MovieNotFoundException;
import se.etraveli.repository.MovieRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MessageSourceService messageSourceService;

    @Override
    public Movie getMovieByCode(String code) {
        Optional<Movie> movieByCode = movieRepository.findMovieByCode(code);
        if (movieByCode.isPresent()) {
            return movieByCode.get();
        }
        throw new MovieNotFoundException(messageSourceService.logMessage("movie.not.found"));
    }
}
