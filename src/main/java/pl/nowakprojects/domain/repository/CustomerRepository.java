package pl.nowakprojects.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.nowakprojects.domain.entity.Customer;

import java.util.List;

/**
 * Created by Mateusz on 22.04.2017.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByEmail(String email);

    Customer findFirstByEmail(String email);
}
