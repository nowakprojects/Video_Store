package pl.nowakprojects.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.nowakprojects.database.entity.Customer;
import pl.nowakprojects.database.entity.Movie;
import pl.nowakprojects.database.repository.CustomersRepository;
import pl.nowakprojects.database.repository.MoviesRepository;

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
