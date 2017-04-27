package pl.nowakprojects.service.interfaces;

import pl.nowakprojects.domain.entity.Customer;
import pl.nowakprojects.domain.entity.Movie;
import pl.nowakprojects.domain.entity.Rental;

import java.util.List;

/**
 * Created by Mateusz on 22.04.2017.
 */
public interface RentalService extends BaseRepositoryService<Rental,Long> {
    boolean rentVideo(Long customerId, Long movieId);
    List<Movie> getAllAvailableMovies();
    boolean isMovieAvailable(Long movieId);
    List<Customer> getAllCustomers();
}