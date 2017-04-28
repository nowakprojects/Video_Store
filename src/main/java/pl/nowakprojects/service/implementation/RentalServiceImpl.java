package pl.nowakprojects.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.nowakprojects.service.interfaces.MovieService;
import pl.nowakprojects.service.interfaces.RentalService;
import pl.nowakprojects.domain.entity.Customer;
import pl.nowakprojects.domain.entity.Movie;
import pl.nowakprojects.domain.entity.Rental;
import pl.nowakprojects.domain.repository.CustomerRepository;
import pl.nowakprojects.domain.repository.RentalRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Mateusz on 22.04.2017.
 */
@Service
public class RentalServiceImpl implements RentalService {
    
    private final RentalRepository rentalRepository;
    private final CustomerRepository customerRepository;
    private final MovieService movieService;

    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository, CustomerRepository customerRepository, MovieService movieService) {
        this.rentalRepository = rentalRepository;
        this.customerRepository = customerRepository;
        this.movieService = movieService;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Rental> findAll() {
        return rentalRepository.findAll();
    }

    @Override
    public Rental save(Rental entity) {
        return rentalRepository.save(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Rental> findOne(Long id) {
        return id == null ? Optional.empty() : Optional.ofNullable(rentalRepository.findOne(id));
    }

    @Override
    public boolean rentMovie(Long customerId, Long movieId) {
        boolean availableToRent = isMovieAvailable(movieId);

        if (availableToRent)
            rentalRepository.save(new Rental(customerRepository.findOne(customerId), movieService.findOne(movieId).get()));

        return availableToRent;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isMovieAvailable(Long movieId) {
        return getAllAvailableMovies().contains(movieService.findOne(movieId).get());
    }

    public List<Movie> getAllAvailableMovies() {
        List<Movie> alreadyRentedMovies =
                rentalRepository.findByReturnDateIsNull().stream()
                        .map(Rental::getMovie)
                        .collect(Collectors.toList());

        return movieService.findAll().stream()
                .filter(movie -> !alreadyRentedMovies.contains(movie))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional
    @Override
    public void returnMovie(Long rentalId) {
        findOne(rentalId).ifPresent(rental -> rental.setReturnDate(LocalDateTime.now()));
    }
}
