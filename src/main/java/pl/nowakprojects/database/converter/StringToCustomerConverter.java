package pl.nowakprojects.database.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.nowakprojects.database.entity.Customer;
import pl.nowakprojects.database.repository.CustomersRepository;

/**
 * Created by Mateusz on 24.04.2017.
 */
@Component
public class StringToCustomerConverter implements Converter<String, Customer> {

    @Autowired
    private CustomersRepository repository;

    @Override
    public Customer convert(String idString) {
        Long id = new Long(idString);
        return repository.findOne(id);
    }

}
