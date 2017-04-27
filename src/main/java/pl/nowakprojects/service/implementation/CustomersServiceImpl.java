package pl.nowakprojects.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pl.nowakprojects.service.interfaces.CustomersService;
import pl.nowakprojects.domain.entity.Customer;
import pl.nowakprojects.domain.repository.CustomersRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Mateusz on 22.04.2017.
 */
@Service
public class CustomersServiceImpl implements CustomersService {

    private final CustomersRepository customersRepository;

    @Autowired
    public CustomersServiceImpl(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return customersRepository.findAll();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Customer save(Customer customer) {
        return customersRepository.save(customer);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Customer> findOne(Long id) {
        return id == null ? Optional.empty() : Optional.ofNullable(customersRepository.findOne(id));
    }

}