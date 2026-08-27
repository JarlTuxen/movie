package dk.ek.movie.service;

import dk.ek.movie.model.Movie;
import dk.ek.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovies(String title, String director) {
        if (title == null && director == null) {
            return movieRepository.findAll();
        }
        return movieRepository.findByFilters(title, director);
    }

    public Movie getMovie(long id) {
        return movieRepository.findById(id);
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }
}
