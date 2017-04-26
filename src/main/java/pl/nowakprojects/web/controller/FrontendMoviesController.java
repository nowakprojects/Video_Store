package pl.nowakprojects.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.nowakprojects.buisnesslogic.interfaces.MoviesService;
import pl.nowakprojects.database.entity.Customer;
import pl.nowakprojects.database.entity.Genre;
import pl.nowakprojects.database.entity.Movie;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Mateusz on 22.04.2017.
 */
@Controller
public class FrontendMoviesController {

    private final static String ATTR_MOVIE = "movie";
    private static final String ATTR_MOVIES_LIST = "moviesList";
    private static final String ATTR_GENRES_LIST = "genresList";

    private final MoviesService moviesService;

    @Autowired
    public FrontendMoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    //TODO: Tak jak ustawianie atrybutu, to mozna ustawic w Form, model zamiast MovieForm
    @RequestMapping("/movies")
    String showMoviesListPage(@RequestParam(defaultValue = "") String title, Model model){

        model.addAttribute(
                ATTR_MOVIES_LIST,
                title.isEmpty() ? moviesService.findAll() : moviesService.findByTitle(title)
        );

        return "movies";
    }

    @RequestMapping("/search")
    String showMoviesSearchPage(Model model){
        return "searchPage";
    }

    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    String movieForm(@PathVariable(name= "id", required = false) Long id, Model model){
        Movie currentMovie = moviesService.findOne(id).orElse(new Movie());
        model.addAttribute(ATTR_MOVIE,currentMovie);
        model.addAttribute(ATTR_GENRES_LIST, Genre.values());
        return "movieForm";
    }

    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    String saveMovieForm(@Valid @ModelAttribute("movie") Movie currentMovie, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "movieForm";

        moviesService.save(currentMovie);
        return "redirect:/movie";
    }


}
