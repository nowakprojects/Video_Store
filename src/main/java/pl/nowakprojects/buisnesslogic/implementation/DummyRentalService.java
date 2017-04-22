package pl.nowakprojects.buisnesslogic.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.nowakprojects.buisnesslogic.interfaces.RentalsService;
import pl.nowakprojects.database.entity.Rental;
import pl.nowakprojects.database.repository.CustomersRepository;
import pl.nowakprojects.database.repository.MoviesRepository;
import pl.nowakprojects.database.repository.RentalsRepository;

import java.util.List;

/**
 * Created by Mateusz on 22.04.2017.
 */
@Service
public class DummyRentalService implements RentalsService {

    private final RentalsRepository rentalsRepository;
    private final CustomersRepository customersRepository;
    private final MoviesRepository moviesRepository;

    @Autowired
    public DummyRentalService(RentalsRepository rentalsRepository, CustomersRepository customersRepository, MoviesRepository moviesRepository) {
        this.rentalsRepository = rentalsRepository;
        this.customersRepository = customersRepository;
        this.moviesRepository = moviesRepository;
        populateDummyData();
    }

    @Override
    public List<Rental> getAllRentals() {
        return rentalsRepository.findAll();
    }

    @Override
    public boolean addRental(Long customerId, Long movieId) {
        rentalsRepository.save(new Rental(customersRepository.findOne(customerId),moviesRepository.findOne(movieId)));
        return true;
    }

    private void populateDummyData(){
        addRental(1L,1L);
        addRental(2L,1L);
    }
}
