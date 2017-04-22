package pl.nowakprojects.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.nowakprojects.database.entity.Movie;

import java.util.List;

/**
 * Created by Mateusz on 22.04.2017.
 */
public interface MoviesRepository extends JpaRepository<Movie,Long>{
    List<Movie> findByTitleContainingIgnoreCase(String title);
}
