package pl.nowakprojects.domain.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.nowakprojects.domain.entity.Customer;

import static org.assertj.core.api.Assertions.*;


/**
 * Created by Mateusz on 28.04.2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository repository;

    @Test
    public void customerWithNullPhoneShouldBeSaved() throws Exception {
        final Customer currentCustomer = new Customer("Brzeszczyszczykiewicz",
                "Grzegorz",
                "grze@jw2ws.pl",
                null,
                "ul. Piękna",
                "Warszawa");

        repository.save(currentCustomer);

        Customer findedCustomer = repository.findFirstByEmail("grze@jw2ws.pl");

        assertThat(findedCustomer.getEmail()).isEqualTo(currentCustomer.getEmail());
        assertThat(findedCustomer.getName()).isEqualTo(currentCustomer.getName());
        assertThat(findedCustomer.getPhone()).isNull();
    }

}
