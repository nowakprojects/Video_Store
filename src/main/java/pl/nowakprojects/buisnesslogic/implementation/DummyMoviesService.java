package pl.nowakprojects.buisnesslogic.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.nowakprojects.buisnesslogic.interfaces.MoviesService;
import pl.nowakprojects.database.entity.Genre;
import pl.nowakprojects.database.entity.Movie;
import pl.nowakprojects.database.repository.MoviesRepository;

import java.util.List;

/**
 * Created by Mateusz on 22.04.2017.
 */
@Service
public class DummyMoviesService implements MoviesService{

    private final MoviesRepository moviesRepository;

    @Autowired
    DummyMoviesService(MoviesRepository moviesRepository){
        this.moviesRepository = moviesRepository;
        populateDummyData();
    }

    public List<Movie> findAll() {
        return moviesRepository.findAll();
    }

    public Movie create(Movie movie) {
        return moviesRepository.save(movie);
    }

    @Override
    public Movie findById(Long id) {
        return moviesRepository.findOne(id);
    }

    @Override
    public List<Movie> findByTitle(String title) {
        return moviesRepository.findByTitleContainingIgnoreCase(title);
    }

    private void populateDummyData(){
        create(new Movie(null,"Star Wars I", Genre.SCIENCE_FICTION,"George Lucas","English","1999"));
        create(new Movie(null,"Star Wars II",Genre.SCIENCE_FICTION,"George Lucas","English","2002"));
        create(new Movie(null,"Star Wars III",Genre.SCIENCE_FICTION,"George Lucas","English","2005"));
        create(new Movie(null,"Star Wars IV",Genre.SCIENCE_FICTION,"George Lucas","English","1977"));
        create(new Movie(null,"Star Wars V",Genre.SCIENCE_FICTION,"George Lucas","English","1980"));
        create(new Movie(null,"Star Wars VI",Genre.SCIENCE_FICTION,"George Lucas","English","1983"));
        create(new Movie(null,"Titanic",Genre.DRAMA,"James Cameron","English","1997"));

    }
}
