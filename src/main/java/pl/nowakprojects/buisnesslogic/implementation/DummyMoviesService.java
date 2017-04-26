package pl.nowakprojects.buisnesslogic.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public Movie save(Movie movie) {
        return moviesRepository.save(movie);
    }

    @Override
    public Optional<Movie> findOne(Long id) {
        return id==null ? Optional.empty() : Optional.ofNullable(moviesRepository.findOne(id));
    }

    @Override
    public List<Movie> findByTitle(String title) {
        return moviesRepository.findByTitleContainingIgnoreCase(title);
    }

    private void populateDummyData(){
        save(new Movie(null,"Star Wars I", Genre.SCIENCE_FICTION,"George Lucas","English",1999));
        save(new Movie(null,"Star Wars II",Genre.SCIENCE_FICTION,"George Lucas","English",2002));
        save(new Movie(null,"Star Wars III",Genre.SCIENCE_FICTION,"George Lucas","English",2005));
        save(new Movie(null,"Star Wars IV",Genre.SCIENCE_FICTION,"George Lucas","English",1977));
        save(new Movie(null,"Star Wars V",Genre.SCIENCE_FICTION,"George Lucas","English",1980));
        save(new Movie(null,"Star Wars VI",Genre.SCIENCE_FICTION,"George Lucas","English",1983));
        save(new Movie(null,"Titanic",Genre.DRAMA,"James Cameron","English",1997));
        /*
        save(new Movie(null,"Star Wars I", Genre.SCIENCE_FICTION,"George Lucas","English","1999"));
        save(new Movie(null,"Star Wars II",Genre.SCIENCE_FICTION,"George Lucas","English","2002"));
        save(new Movie(null,"Star Wars III",Genre.SCIENCE_FICTION,"George Lucas","English","2005"));
        save(new Movie(null,"Star Wars IV",Genre.SCIENCE_FICTION,"George Lucas","English","1977"));
        save(new Movie(null,"Star Wars V",Genre.SCIENCE_FICTION,"George Lucas","English","1980"));
        save(new Movie(null,"Star Wars VI",Genre.SCIENCE_FICTION,"George Lucas","English","1983"));
        save(new Movie(null,"Titanic",Genre.DRAMA,"James Cameron","English","1997"));
*/
    }
}
