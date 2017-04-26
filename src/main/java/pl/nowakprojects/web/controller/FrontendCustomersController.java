package pl.nowakprojects.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
public class FrontendCustomersController {

    private static final String ATTR_CUSTOMERS = "customers";

    private CustomersService customersService;

    @Autowired
    public FrontendCustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }

    @RequestMapping(value = "/customers",method = RequestMethod.GET)
    public String customers(Model model){
        model.addAttribute(ATTR_CUSTOMERS,customersService.findAll());
        return "customers";
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String customerForm(@RequestParam(value = "id", required = false) Long id, Model model){
        Customer customer = customersService.findOne(id);
        if(customer==null) {
            customer = new Customer();
        }

        model.addAttribute("customer",customer);
        return "customerForm";
    }

    @RequestMapping(value = "/customer", method = RequestMethod.DELETE)
    public String deleteCustomer(@RequestParam(value = "id") Long id){
        //customersService.delete(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public String customer(@Valid @ModelAttribute("newCustomer") Customer newCustomer, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors())
            return "customerForm";
        else{
            customersService.save(newCustomer);
        }

        return "redirect:/customers";
    }

}
