package pl.nowakprojects.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.nowakprojects.buisnesslogic.interfaces.CustomersService;
import pl.nowakprojects.database.entity.Customer;
import pl.nowakprojects.database.entity.Genre;
import pl.nowakprojects.database.entity.Movie;
import pl.nowakprojects.database.repository.CustomersRepository;

import javax.validation.Valid;

/**
 * Created by Mateusz on 23.04.2017.
 */
@Controller
public class CustomersController {

    private static final String ATTR_CUSTOMER = "customer";
    private static final String ATTR_CUSTOMERS_LIST = "customers";

    private CustomersService customersService;

    @Autowired
    public CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String showCustomersList(Model model) {
        model.addAttribute(ATTR_CUSTOMERS_LIST, customersService.findAll());
        return "customers";
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String showCustomerForm(@RequestParam(value = "id", required = false) Long id, Model model) {
        Customer currentCustomer = customersService.findOne(id).orElseGet(() -> new Customer());

        model.addAttribute(ATTR_CUSTOMER, currentCustomer);

        return "customerForm";
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public String submitCustomer(
            @Valid @ModelAttribute("customer") Customer currentCustomer,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            return "customerForm";

        customersService.save(currentCustomer);
        return "redirect:/customers";
    }

}
