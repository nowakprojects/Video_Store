package pl.nowakprojects.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.nowakprojects.service.interfaces.CustomerService;
import pl.nowakprojects.domain.entity.Customer;

import javax.validation.Valid;

/**
 * Created by Mateusz on 23.04.2017.
 */
@Controller
public class CustomerController {

    private static final String ATTR_CUSTOMER = "customer";
    private static final String ATTR_CUSTOMERS_LIST = "customers";

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public String showCustomersList(Model model) {
        model.addAttribute(ATTR_CUSTOMERS_LIST, customerService.findAll());
        return "customers";
    }

    @GetMapping("/customer")
    public String showCustomerForm(@RequestParam(value = "id", required = false) Long id, Model model) {
        Customer currentCustomer = customerService.findOne(id).orElseGet(Customer::new);

        model.addAttribute(ATTR_CUSTOMER, currentCustomer);

        return "customerForm";
    }

    @PostMapping("/customer")
    public String submitCustomer(
            @Valid @ModelAttribute("customer") Customer currentCustomer,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors())
            return "customerForm";

        customerService.save(currentCustomer);
        return "redirect:/customers";
    }

}
