package pl.nowakprojects.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.nowakprojects.database.entity.Rental;

import java.util.Date;
import java.util.List;

/**
 * Created by Mateusz on 22.04.2017.
 */
public interface RentalsRepository extends JpaRepository<Rental,Long> {
    List<Rental> findByMovieId(Long movieId);
    List<Rental> findByCustomerIdAndMovieId(Long customerId, Long movieId);
    List<Rental> findByCustomerId(Long customerId);
    List<Rental> findByRentDateBetween(Date startDate, Date endDate);
    List<Rental> findByRentDateAfter(Date startDate);
    List<Rental> findByRentDateBefore(Date endDate);
    List<Rental> findByReturnDateIsNull();
    List<Rental> findByReturnDateIsNotNull();
}
