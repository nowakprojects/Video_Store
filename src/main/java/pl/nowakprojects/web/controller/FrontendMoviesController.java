package pl.nowakprojects.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.nowakprojects.buisnesslogic.interfaces.MoviesService;
import pl.nowakprojects.database.entity.Movie;
import pl.nowakprojects.database.repository.MoviesRepository;
import pl.nowakprojects.web.dto.MovieForm;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Mateusz on 22.04.2017.
 */
@Controller
public class FrontendMoviesController {

    private final MoviesService moviesService;

    @Autowired
    public FrontendMoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @RequestMapping("/movies")
    String moviesList(@RequestParam(defaultValue = "") String title, Model model){
        List<Movie> movieListToShow;

        if(title.isEmpty())
            movieListToShow = moviesService.getAllMovies();
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
    String displayMovie(MovieForm movieForm){
        return "movieForm";
    }

    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    String saveMovie(@Valid MovieForm movieForm, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "movieForm";

        return "redirect:/movie";
    }


}
