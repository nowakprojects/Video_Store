package pl.nowakprojects.domain.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import pl.nowakprojects.TestConfig;
import pl.nowakprojects.domain.entity.Customer;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Mateusz on 28.04.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository repository;

    @Before
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void customerTableShouldBeEmpty() {
        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    public void customerShouldBeSaved() {
        repository.save(
                new Customer(
                        null,
                        "Jan",
                        "Kowalski",
                        "jan.kowalski@gmail.com",
                        "234123123",
                        "st. Street 1/1",
                        "City 1"
                )
        );
        assertThat(repository.findAll()).isNotEmpty();
    }

    @Test
    public void customerShouldBeEdited() {
        assertThat(repository.findAll()).isEmpty();
        customerShouldBeSaved();
        Customer newCustomer = new Customer(
                1L,
                "Marian",
                "Kowalski",
                "jan.kowalski@gmail.com",
                "234123123",
                "st. Street 1/1",
                "City 1"
        );
        repository.save(newCustomer);

        assertThat(repository.findAll()).isNotEmpty();
        assertThat(repository.findAll()).hasSize(1);
        assertThat(repository.findFirstByEmail("jan.kowalski@gmail.com")).isEqualTo(newCustomer);
    }


}
