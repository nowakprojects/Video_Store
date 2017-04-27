package pl.nowakprojects.service.interfaces;

import pl.nowakprojects.domain.entity.Movie;

import java.util.List;

/**
 * Created by Mateusz on 22.04.2017.
 */
public interface MoviesService extends BaseRepositoryService<Movie,Long> {
    List<Movie> findByTitle(String title);
}
