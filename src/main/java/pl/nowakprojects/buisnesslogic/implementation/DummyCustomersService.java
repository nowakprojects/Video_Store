package pl.nowakprojects.buisnesslogic.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.nowakprojects.buisnesslogic.interfaces.CustomersService;
import pl.nowakprojects.database.entity.Customer;
import pl.nowakprojects.database.repository.CustomersRepository;

import java.util.List;

/**
 * Created by Mateusz on 22.04.2017.
 */
@Service
public class DummyCustomersService implements CustomersService {

    private final CustomersRepository customersRepository;

    @Autowired
    public DummyCustomersService(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
        populateDummyData();
    }

    public List<Customer> getAllCustomers() {
        return customersRepository.findAll();
    }

    public Customer saveCustomer(Customer customer) {
        return customersRepository.save(customer);
    }

    private void populateDummyData(){
        saveCustomer(new Customer(
                null,
                "Jan",
                "Kowalski",
                "jan.kowalski@gmail.com",
                "234123123",
                "st. Street 1/1",
                "City 1"
                )
        );

        saveCustomer(new Customer(
                        null,
                        "Jan",
                        "Kowalski",
                        "koval12@gmail.com",
                        "233223123",
                        "st. Street 1/2",
                        "City 3"
                )
        );

        saveCustomer(new Customer(
                        null,
                        "Anna",
                        "Nowak",
                        "nowakowa@gmail.com",
                        "233223113",
                        "st. Street 4/4",
                        "City 5"
                )
        );


        saveCustomer(new Customer(
                        null,
                        "Mateusz",
                        "Nowak",
                        "novakow@gmail.com",
                        "233223113",
                        "st. Street 4/4",
                        "City 5"
                )
        );


        saveCustomer(new Customer(
                        null,
                        "Zbigniew",
                        "Wójcik",
                        "wojtasz@gmail.com",
                        "233223444",
                        "st. AStreet 10/4",
                        "City 12"
                )
        );
    }
}
