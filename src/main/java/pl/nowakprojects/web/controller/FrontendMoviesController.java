package pl.nowakprojects.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.nowakprojects.buisnesslogic.interfaces.MoviesService;
import pl.nowakprojects.database.entity.Movie;
import pl.nowakprojects.database.repository.MoviesRepository;

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


}
