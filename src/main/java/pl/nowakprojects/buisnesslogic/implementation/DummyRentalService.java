package pl.nowakprojects.buisnesslogic.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.nowakprojects.buisnesslogic.interfaces.RentalsService;
import pl.nowakprojects.database.entity.Customer;
import pl.nowakprojects.database.entity.Movie;
import pl.nowakprojects.database.entity.Rental;
import pl.nowakprojects.database.repository.CustomersRepository;
import pl.nowakprojects.database.repository.MoviesRepository;
import pl.nowakprojects.database.repository.RentalsRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mateusz on 22.04.2017.
 */
@Service
public class DummyRentalService implements RentalsService {

    private final RentalsRepository rentalsRepository;
    private final CustomersRepository customersRepository;
    private final MoviesRepository moviesRepository;

    @Autowired
    public DummyRentalService(RentalsRepository rentalsRepository, CustomersRepository customersRepository, MoviesRepository moviesRepository) {
        this.rentalsRepository = rentalsRepository;
        this.customersRepository = customersRepository;
        this.moviesRepository = moviesRepository;
        populateDummyData();
    }

    @Override
    public List<Rental> findAll() {
        return rentalsRepository.findAll();
    }

    @Override
    public Rental save(Rental entity) {
        return rentalsRepository.save(entity);
    }

    @Override
    public Rental findOne(Long id) {
        return rentalsRepository.findOne(id);
    }

    @Override
    public boolean rentVideo(Long customerId, Long movieId) {
        boolean availableToRent = isMovieAvailable(movieId);

        if(availableToRent)
            rentalsRepository.save(new Rental(customersRepository.findOne(customerId), moviesRepository.findOne(movieId)));

        return availableToRent;
    }

    @Override
    public boolean isMovieAvailable(Long movieId) {
        return getAllAvailableMovies().contains(moviesRepository.findOne(movieId));
    }

    public List<Movie> getAllAvailableMovies(){
        List<Movie> alreadyRentedMovies =
                rentalsRepository.findByReturnDateIsNull().stream()
                        .map(Rental::getMovie).collect(Collectors.toList());

        List<Movie> result = moviesRepository.findAll().stream()
                .filter(movie -> !alreadyRentedMovies.contains(movie)).collect(Collectors.toList());

        return result;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customersRepository.findAll();
    }

    private void populateDummyData(){
        rentVideo(1L,1L);
        rentVideo(2L,2L);
        rentVideo(3L,3L);
        rentVideo(3L,4L);
    }
}
