package pl.nowakprojects.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.nowakprojects.domain.entity.Rental;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Mateusz on 22.04.2017.
 */
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByMovieId(Long movieId);
    List<Rental> findByCustomerIdAndMovieId(Long customerId, Long movieId);
    List<Rental> findByCustomerId(Long customerId);
    List<Rental> findByRentDateBetween(Date startDate, Date endDate);
    List<Rental> findByRentDateAfter(Date startDate);
    List<Rental> findByRentDateBefore(Date endDate);
    List<Rental> findByReturnDateIsNull();
    List<Rental> findByReturnDateIsNotNull();

    Optional<Rental> findFirstByMovieIdAndReturnDateIsNull(Long movieId);
}
