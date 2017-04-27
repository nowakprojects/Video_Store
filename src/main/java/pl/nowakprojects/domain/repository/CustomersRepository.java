package pl.nowakprojects.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.nowakprojects.domain.entity.Customer;

/**
 * Created by Mateusz on 22.04.2017.
 */
public interface CustomersRepository extends JpaRepository<Customer,Long>{
}