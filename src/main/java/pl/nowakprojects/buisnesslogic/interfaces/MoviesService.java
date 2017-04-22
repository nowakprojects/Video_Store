package pl.nowakprojects.buisnesslogic.interfaces;

import pl.nowakprojects.database.entity.Movie;

import java.util.List;

/**
 * Created by Mateusz on 22.04.2017.
 */
public interface MoviesService {
    List<Movie> findByTitle(String title);
}
