package pl.nowakprojects.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.nowakprojects.buisnesslogic.interfaces.RentalService;
import pl.nowakprojects.database.entity.Rental;

import java.util.List;

/**
 * Created by Mateusz on 22.04.2017.
 */
@RequestMapping("/api/rentals")
@RestController
public class RentalsController {

    private final RentalService rentalService;

    @Autowired
    public RentalsController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    ResponseEntity<List<Rental>> getAllRentals(){
        List<Rental> allRentals = rentalService.findAll();
        HttpStatus httpStatus = allRentals.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(allRentals, httpStatus);
    }

}
