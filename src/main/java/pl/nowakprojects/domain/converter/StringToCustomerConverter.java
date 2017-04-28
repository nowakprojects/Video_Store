package pl.nowakprojects.domain.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.nowakprojects.domain.entity.Customer;
import pl.nowakprojects.domain.repository.CustomerRepository;

/**
 * Created by Mateusz on 24.04.2017.
 */
@Component
public class StringToCustomerConverter implements Converter<String, Customer> {

    private final CustomerRepository repository;

    @Autowired
    public StringToCustomerConverter(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer convert(String idString) {
        Long id = new Long(idString);
        return repository.findOne(id);
    }

}
