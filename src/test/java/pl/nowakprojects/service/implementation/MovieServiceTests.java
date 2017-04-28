package pl.nowakprojects.service.implementation;


import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import pl.nowakprojects.TestConfig;
import pl.nowakprojects.domain.entity.Genre;
import pl.nowakprojects.domain.entity.Movie;
import pl.nowakprojects.domain.repository.CustomerRepository;
import pl.nowakprojects.domain.repository.MovieRepository;
import pl.nowakprojects.service.interfaces.MovieService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Mateusz on 28.04.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class}, loader = AnnotationConfigContextLoader.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class MovieServiceTests {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    @Before
    public void setUp() {
        movieRepository.deleteAll();
    }

    @Test
    public void movieShouldBeSaved() throws Exception {
        movieService.save(
                new Movie(null,
                        "Star Wars I",
                        Genre.SCIENCE_FICTION,
                        "George Lucas",
                        "English",
                        1999)
        );
        assertThat(movieService.findAll()).isNotEmpty();
        assertThat(movieService.findAll()).hasSize(1);
    }


    @Test
    public void movieShouldBeUpdated() throws Exception {
        movieService.save(
                new Movie(null,
                        "Star Wars I",
                        Genre.SCIENCE_FICTION,
                        "George Lucas",
                        "English",
                        1999)
        );
        movieService.save(
                new Movie(movieService.findAll().get(0).getId(),
                        "PGS Software",
                        Genre.SCIENCE_FICTION,
                        "George Lucas",
                        "English",
                        1999)
        );
        assertThat(movieService.findAll()).isNotEmpty();
        assertThat(movieService.findAll()).hasSize(1);
        assertThat(movieService.findOne(1L).get().getTitle()).isEqualTo("PGS Software");
    }

    @Test
    public void movieShouldBeSearchableByPartOfTitle() throws Exception {
        movieService.save(
                new Movie(null,
                        "Star Wars I",
                        Genre.SCIENCE_FICTION,
                        "George Lucas",
                        "English",
                        1999)
        );
        movieService.save(
                new Movie(null,
                        "Star Wars II",
                        Genre.SCIENCE_FICTION,
                        "George Lucas",
                        "English",
                        1999)
        );
        movieService.save(
                new Movie(null,
                        "Star Wars III",
                        Genre.SCIENCE_FICTION,
                        "George Lucas",
                        "English",
                        1999)
        );

        assertThat(movieService.findByTitle("ars")).hasSize(3);
        assertThat(movieService.findByTitle("III")).hasSize(1);
    }


    @Test
    public void findOneNotExistsMovieIdShouldReturnOptionalEmpty() throws Exception {
        assertThat(movieService.findOne(2L)).isEqualTo(Optional.empty());
    }
}
