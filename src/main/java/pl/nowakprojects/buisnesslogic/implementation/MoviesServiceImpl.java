package pl.nowakprojects.buisnesslogic.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.nowakprojects.buisnesslogic.interfaces.MoviesService;
import pl.nowakprojects.database.entity.Genre;
import pl.nowakprojects.database.entity.Movie;
import pl.nowakprojects.database.repository.MoviesRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Mateusz on 22.04.2017.
 */
@Service
public class MoviesServiceImpl implements MoviesService{

    private final MoviesRepository moviesRepository;

    @Autowired
    MoviesServiceImpl(MoviesRepository moviesRepository){
        this.moviesRepository = moviesRepository;
    }

    @Transactional(readOnly = true)
    public List<Movie> findAll() {
        return moviesRepository.findAll();
    }

    @Transactional
    public Movie save(Movie movie) {
        return moviesRepository.save(movie);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Movie> findOne(Long id) {
        return id==null ? Optional.empty() : Optional.ofNullable(moviesRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Movie> findByTitle(String title) {
        return moviesRepository.findByTitleContainingIgnoreCase(title);
    }

}
