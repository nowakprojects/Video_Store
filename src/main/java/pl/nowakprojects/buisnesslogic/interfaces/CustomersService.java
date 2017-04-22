package pl.nowakprojects.buisnesslogic.interfaces;

import pl.nowakprojects.database.entity.Customer;
import pl.nowakprojects.database.entity.Movie;

import java.util.List;

/**
 * Created by Mateusz on 22.04.2017.
 */
public interface CustomersService {
    Customer saveCustomer(Customer customer);
    List<Customer> getAllCustomers();
}
