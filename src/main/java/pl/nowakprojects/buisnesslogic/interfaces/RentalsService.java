package pl.nowakprojects.buisnesslogic.interfaces;

import pl.nowakprojects.database.entity.Customer;
import pl.nowakprojects.database.entity.Movie;
import pl.nowakprojects.database.entity.Rental;

import java.util.List;

/**
 * Created by Mateusz on 22.04.2017.
 */
public interface RentalsService extends BaseRepositoryService<Rental,Long> {
    boolean rentVideo(Long customerId, Long movieId);
    List<Movie> getAllAvailableMovies();
    boolean isMovieAvailable(Long movieId);
    List<Customer> getAllCustomers();
}
