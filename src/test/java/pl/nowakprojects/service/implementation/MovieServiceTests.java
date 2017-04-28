package pl.nowakprojects.service.implementation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;
import pl.nowakprojects.domain.entity.Genre;
import pl.nowakprojects.domain.entity.Movie;
import pl.nowakprojects.domain.repository.MovieRepository;
import pl.nowakprojects.service.interfaces.MovieService;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Mateusz on 28.04.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MovieServiceTests {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    @BeforeTransaction
    public void beforeTransaction() {
        movieRepository.deleteAll();
    }

    @Test
    public void movieShouldBeSaved() throws Exception {
        assertThat(movieService.findAll()).isEmpty();
        movieService.save(new Movie("Title1", Genre.ACTION, "Director1", "Language1", 1999));
        assertThat(movieService.findAll()).isNotEmpty();
    }


}
