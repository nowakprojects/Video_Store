package pl.nowakprojects.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.nowakprojects.domain.entity.Movie;
import pl.nowakprojects.domain.repository.MovieRepository;
import pl.nowakprojects.service.interfaces.MovieService;

import java.util.List;
import java.util.Optional;

/**
 * Created by Mateusz on 22.04.2017.
 */
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional(readOnly = true)
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Transactional
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Movie> findOne(Long id) {
        return id == null ? Optional.empty() : Optional.ofNullable(movieRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Movie> findByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

}
