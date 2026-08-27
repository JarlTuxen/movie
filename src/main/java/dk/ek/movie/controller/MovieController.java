package dk.ek.movie.controller;

import dk.ek.movie.model.Movie;
import dk.ek.movie.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String director) {

        List<Movie> movies = movieService.getMovies(title, director);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable long id) {
        Movie movie = movieService.getMovie(id);
        return movie == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(movie);
    }

    @PostMapping()
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.createMovie(movie));
    }
}
