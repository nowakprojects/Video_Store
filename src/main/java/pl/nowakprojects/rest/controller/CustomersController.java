package pl.nowakprojects.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.nowakprojects.buisnesslogic.interfaces.CustomersService;
import pl.nowakprojects.database.entity.Customer;
import pl.nowakprojects.database.entity.Movie;

import java.util.List;

/**
 * Created by Mateusz on 22.04.2017.
 */
@RestController
@RequestMapping("/api/customers")
public class CustomersController {

    private final CustomersService customersService;

    @Autowired
    public CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> allCustomersList = customersService.getAllCustomers();
        HttpStatus httpStatus = allCustomersList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(allCustomersList, httpStatus);
    }

}

