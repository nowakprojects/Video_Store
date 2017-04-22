package pl.nowakprojects.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.nowakprojects.database.entity.Customer;
import pl.nowakprojects.database.entity.Movie;

/**
 * Created by Mateusz on 22.04.2017.
 */
public interface CustomersRepository extends JpaRepository<Customer,Long>{
}
