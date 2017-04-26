package pl.nowakprojects.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.nowakprojects.buisnesslogic.interfaces.MoviesService;
import pl.nowakprojects.database.entity.Movie;
import pl.nowakprojects.web.dto.MovieForm;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by Mateusz on 22.04.2017.
 */
@Controller
public class FrontendMoviesController {

    private final static String ATTR_MOVIE = "attr_movie";

    private final MoviesService moviesService;

    @Autowired
    public FrontendMoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    //TODO: Tak jak ustawianie atrybutu, to mozna ustawic w Form, model zamiast MovieForm
    @RequestMapping("/movies")
    String movies(@RequestParam(defaultValue = "") String title, Model model){
        List<Movie> movieListToShow;

        if(title.isEmpty())
            movieListToShow = moviesService.findAll();
        else
            movieListToShow = moviesService.findByTitle(title);

        model.addAttribute("movies", movieListToShow);
        return "movies";
    }

    @RequestMapping("/search")
    String search(Model model){
        return "searchPage";
    }

    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    String movieForm(@PathVariable(name= "id", required = false) Long movieId, Model model){
        Optional<Movie> movie = moviesService.findOne(movieId);

        model.addAttribute(ATTR_MOVIE,movie.orElse(new Movie()));

        return "movieForm";
    }

    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    String saveMovie(@Valid MovieForm movieForm, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "movieForm";
        else{
            moviesService.save(
                    new Movie(
                            movieForm.getId(),
                            movieForm.getTitle(),
                            movieForm.getGenre(),
                            movieForm.getDirector(),
                            movieForm.getLanguage(),
                            movieForm.getReleaseYear()
                    )
            );
        }

        return "redirect:/movie";
    }


}
