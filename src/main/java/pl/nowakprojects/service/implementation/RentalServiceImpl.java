package pl.nowakprojects.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.nowakprojects.service.interfaces.RentalService;
import pl.nowakprojects.domain.entity.Customer;
import pl.nowakprojects.domain.entity.Movie;
import pl.nowakprojects.domain.entity.Rental;
import pl.nowakprojects.domain.repository.CustomersRepository;
import pl.nowakprojects.domain.repository.MoviesRepository;
import pl.nowakprojects.domain.repository.RentalsRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Mateusz on 22.04.2017.
 */
@Service
public class RentalServiceImpl implements RentalService {

    private final RentalsRepository rentalsRepository;
    private final CustomersRepository customersRepository;
    private final MoviesRepository moviesRepository;

    @Autowired
    public RentalServiceImpl(RentalsRepository rentalsRepository, CustomersRepository customersRepository, MoviesRepository moviesRepository) {
        this.rentalsRepository = rentalsRepository;
        this.customersRepository = customersRepository;
        this.moviesRepository = moviesRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Rental> findAll() {
        return rentalsRepository.findAll();
    }

    @Override
    public Rental save(Rental entity) {
        return rentalsRepository.save(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Rental> findOne(Long id) {
        return id==null ? Optional.empty() : Optional.ofNullable(rentalsRepository.findOne(id));
    }

    @Override
    public boolean rentVideo(Long customerId, Long movieId) {
        boolean availableToRent = isMovieAvailable(movieId);

        if (availableToRent)
            rentalsRepository.save(new Rental(customersRepository.findOne(customerId), moviesRepository.findOne(movieId)));

        return availableToRent;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isMovieAvailable(Long movieId) {
        return getAllAvailableMovies().contains(moviesRepository.findOne(movieId));
    }

    public List<Movie> getAllAvailableMovies() {
        List<Movie> alreadyRentedMovies =
                rentalsRepository.findByReturnDateIsNull().stream()
                        .map(Rental::getMovie)
                        .collect(Collectors.toList());

        return moviesRepository.findAll().stream()
                .filter(movie -> !alreadyRentedMovies.contains(movie))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Customer> getAllCustomers() {
        return customersRepository.findAll();
    }

}
