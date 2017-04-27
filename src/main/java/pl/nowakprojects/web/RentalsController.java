package pl.nowakprojects.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.nowakprojects.service.interfaces.RentalService;
import pl.nowakprojects.domain.entity.Rental;

import javax.validation.Valid;

/**
 * Created by Mateusz on 24.04.2017.
 */
@Controller
public class RentalsController {

    private static final String ATTR_RENTAL = "rental";
    private static final String ATTR_RENTAL_LIST = "rental_list";
    private static final String ATTR_MOVIES = "movies";
    private static final String ATTR_CUSTOMERS = "customers";

    private final RentalService rentalService;

    @Autowired
    public RentalsController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @RequestMapping("/")
    public String index(){
        return "rentalForm";
    }

    @RequestMapping(value = "/history",method = RequestMethod.GET)
    public String allRentalList(Model model){
        model.addAttribute(ATTR_RENTAL_LIST, rentalService.findAll());
        return "rentalList";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String rentalForm(@RequestParam(value = "id", required = false) Long id, Model model){
        Rental currentRental = rentalService.findOne(id).orElse(new Rental());

        model.addAttribute(ATTR_RENTAL,currentRental);
        model.addAttribute(ATTR_MOVIES, rentalService.getAllAvailableMovies());
        model.addAttribute(ATTR_CUSTOMERS, rentalService.getAllCustomers());

        return "rentalForm";
    }

    @RequestMapping(value = "/rent", method = RequestMethod.POST)
    public String rent(@Valid @ModelAttribute("rental") Rental rental, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors())
            return "redirect:/";
        else{
            rentalService.save(rental);
        }

        return "redirect:/history";
    }


}
