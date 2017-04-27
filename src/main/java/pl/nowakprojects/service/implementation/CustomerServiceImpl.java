package pl.nowakprojects.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pl.nowakprojects.domain.entity.Customer;
import pl.nowakprojects.domain.repository.CustomerRepository;
import pl.nowakprojects.service.interfaces.CustomerService;

import java.util.List;
import java.util.Optional;

/**
 * Created by Mateusz on 22.04.2017.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Customer> findOne(Long id) {
        return id == null ? Optional.empty() : Optional.ofNullable(customerRepository.findOne(id));
    }

}