package pl.nowakprojects.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.nowakprojects.domain.entity.Rental;
import pl.nowakprojects.service.interfaces.RentalService;

import javax.validation.Valid;

/**
 * Created by Mateusz on 24.04.2017.
 */
@Controller
public class RentalController {

    private static final String ATTR_RENTAL = "rental";
    private static final String ATTR_RENTAL_LIST = "rental_list";
    private static final String ATTR_MOVIES = "movies";
    private static final String ATTR_CUSTOMERS = "customers";

    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/history")
    public String allRentalList(Model model) {
        model.addAttribute(ATTR_RENTAL_LIST, rentalService.findAll());
        return "rentalHistory";
    }

    @GetMapping("/")
    public String rentalForm(@RequestParam(value = "id", required = false) Long id, Model model) {
        Rental currentRental = rentalService.findOne(id).orElseGet(Rental::new);

        model.addAttribute(ATTR_RENTAL, currentRental);
        model.addAttribute(ATTR_MOVIES, rentalService.getAllAvailableMovies());
        model.addAttribute(ATTR_CUSTOMERS, rentalService.getAllCustomers());

        return "rentalForm";
    }

    @PostMapping("/rent")
    public String rent(@Valid @ModelAttribute("rental") Rental rental, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "redirect:/";
        else {
            rentalService.save(rental);
        }

        return "redirect:/history";
    }


    @PutMapping("/return")
    public String returnMovie(@RequestParam(value = "id") Long rentalId) {
        rentalService.returnMovie(rentalId);
        return "redirect:/history";
    }

}
