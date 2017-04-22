package pl.nowakprojects.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.nowakprojects.buisnesslogic.interfaces.RentalsService;
import pl.nowakprojects.database.entity.Rental;

import java.util.List;

/**
 * Created by Mateusz on 22.04.2017.
 */
@RequestMapping("/rentals")
@RestController
public class RentalsController {

    private final RentalsService rentalsService;

    @Autowired
    public RentalsController(RentalsService rentalsService) {
        this.rentalsService = rentalsService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    ResponseEntity<List<Rental>> getAllRentals(){
        List<Rental> allRentals = rentalsService.getAllRentals();
        HttpStatus httpStatus = allRentals.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(allRentals, httpStatus);
    }

}
