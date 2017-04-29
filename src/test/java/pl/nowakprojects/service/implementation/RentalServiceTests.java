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
import pl.nowakprojects.TestConfig;
import pl.nowakprojects.domain.entity.Customer;
import pl.nowakprojects.domain.entity.Genre;
import pl.nowakprojects.domain.entity.Movie;
import pl.nowakprojects.domain.entity.Rental;
import pl.nowakprojects.domain.repository.CustomerRepository;
import pl.nowakprojects.domain.repository.MovieRepository;
import pl.nowakprojects.domain.repository.RentalRepository;
import pl.nowakprojects.service.interfaces.RentalService;

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
public class RentalServiceTests {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private RentalService rentalService;

    private static boolean setUpIsDone = false;

    @Before
    public void setUp() {
        rentalRepository.deleteAll();
        setUpOnce();
    }

    private void setUpOnce() {
        if (setUpIsDone) {
            return;
        }
        populateDummyMoviesAndCustomersData();
        setUpIsDone = true;
    }

    private void populateDummyMoviesAndCustomersData() {
        movieRepository.save(new Movie(1L, "Star Wars I", Genre.SCIENCE_FICTION, "George Lucas", "English", 1999));
        movieRepository.save(new Movie(2L, "Star Wars II", Genre.SCIENCE_FICTION, "George Lucas", "English", 2002));
        movieRepository.save(new Movie(3L, "Star Wars III", Genre.SCIENCE_FICTION, "George Lucas", "English", 2005));
        movieRepository.save(new Movie(4L, "Star Wars IV", Genre.SCIENCE_FICTION, "George Lucas", "English", 1977));
        movieRepository.save(new Movie(5L, "Star Wars V", Genre.SCIENCE_FICTION, "George Lucas", "English", 1980));
        movieRepository.save(new Movie(6L, "Star Wars VI", Genre.SCIENCE_FICTION, "George Lucas", "English", 1983));
        movieRepository.save(new Movie(7L, "Titanic", Genre.DRAMA, "James Cameron", "English", 1997));

        customerRepository.save(new Customer(1L, "Jan", "Kowalski", "jan.kowalski@gmail.com", "234123123", "st. Street 1/1", "City"));
        customerRepository.save(new Customer(2L, "Jan", "Kowalski", "koval12@gmail.com", "233223123", "st. Street 1/2", "City"));
        customerRepository.save(new Customer(3L, "Anna", "Nowak", "nowakowa@gmail.com", "233223113", "st. Street 4/4", "City"));
        customerRepository.save(new Customer(4L, "Mateusz", "Nowak", "novakow@gmail.com", "233223113", "st. Street 4/4", "City"));
    }

    @Test
    public void allRentalListShouldBeEmpty() {
        assertThat(rentalService.findAll()).isEmpty();
    }

    @Test
    public void saveShouldInsertRental() {
        rentOneMovie();
        assertThat(rentalService.findAll()).hasSize(1);
    }

    @Test
    public void rentShouldDecreaseAvailableMoviesListSize() {
        int beginAvailableMoviesSize = rentalService.getAllAvailableMovies().size();
        rentOneMovie();
        assertThat(rentalService.getAllAvailableMovies().size()).isEqualTo(beginAvailableMoviesSize - 1);
    }
    

    private Optional<Rental> rentOneMovie() {
        Customer customer = customerRepository.findAll().get(0);
        Movie movie = movieRepository.findAll().get(0);
        return rentalService.rentMovie(customer.getId(), movie.getId());
    }

}
