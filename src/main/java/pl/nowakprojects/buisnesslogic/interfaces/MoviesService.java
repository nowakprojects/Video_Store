package pl.nowakprojects.buisnesslogic.interfaces;

import pl.nowakprojects.database.entity.Movie;

import java.util.List;

/**
 * Created by Mateusz on 22.04.2017.
 */
public interface MoviesService {
    Movie saveMovie(Movie movie);
    List<Movie> getAllMovies();
    List<Movie> findByTitle(String title);
}
