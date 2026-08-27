package dk.ek.movie.repository;

import dk.ek.movie.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepository {

    private final List<Movie> movies = new ArrayList<>();
    private long nextId = 1;
    public MovieRepository() {
        populate();
    }

    public List<Movie> findAll() {
        return new ArrayList<>(movies);
    }

    public Movie findById(long id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    public List<Movie> findByFilters(String title, String director){
        List<Movie> foundMovies = new ArrayList<>();
        for (Movie movie : movies) {
            boolean matchesTitle = (title == null) || movie.getTitle().equalsIgnoreCase(title);

            boolean matchesDirector = (director == null) || movie.getDirector().equalsIgnoreCase(director);

            if (matchesTitle && matchesDirector) {
                foundMovies.add(movie);
            }
        }
        return foundMovies;
    }

    public Movie save(Movie movie) {
        movie.setId(nextId++);
        movies.add(movie);
        return movie;
    }

    private void populate() {
        save(new Movie("The Godfather", "Francis Ford Coppola"));
        save(new Movie("Pulp Fiction", "Quentin Tarantino"));
        save(new Movie("The Dark Knight", "Christopher Nolan"));
        save(new Movie("Inception", "Christopher Nolan"));
        save(new Movie("Kill Bill", "Quentin Tarantino"));
        save(new Movie("Forrest Gump", "Robert Zemeckis"));
    }
}
