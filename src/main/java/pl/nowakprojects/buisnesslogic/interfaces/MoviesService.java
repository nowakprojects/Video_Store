package pl.nowakprojects.buisnesslogic.interfaces;

import pl.nowakprojects.database.entity.Movie;

import java.util.List;
import java.util.Optional;

/**
 * Created by Mateusz on 22.04.2017.
 */
public interface MoviesService extends BaseRepositoryService<Movie,Long> {
    List<Movie> findByTitle(String title);
}
