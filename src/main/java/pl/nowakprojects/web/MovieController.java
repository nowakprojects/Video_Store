package pl.nowakprojects.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.nowakprojects.domain.entity.Genre;
import pl.nowakprojects.domain.entity.Movie;
import pl.nowakprojects.service.interfaces.MovieService;

import javax.validation.Valid;

/**
 * Created by Mateusz on 22.04.2017.
 */
@Controller
public class MovieController {

    private final static String ATTR_MOVIE = "movie";
    private static final String ATTR_MOVIES_LIST = "moviesList";
    private static final String ATTR_GENRES_LIST = "genresList";

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public String showMoviesList(@RequestParam(defaultValue = "") String title, Model model) {
        model.addAttribute(
                ATTR_MOVIES_LIST,
                title.isEmpty() ? movieService.findAll() : movieService.findByTitle(title)
        );
        return "movies";
    }

    @GetMapping("/search")
    public String showMoviesSearchPage(Model model) {
        return "searchPage";
    }

    @GetMapping("/movie")
    public String showMovieForm(@RequestParam(name = "id", required = false) Long id, Model model) {
        Movie currentMovie = movieService.findOne(id).orElseGet(Movie::new);
        model.addAttribute(ATTR_MOVIE, currentMovie);
        model.addAttribute(ATTR_GENRES_LIST, Genre.values());
        return "movieForm";
    }

    @PostMapping("/movie")
    public String submitMovie(
            @Valid @ModelAttribute("movie") Movie currentMovie,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(ATTR_GENRES_LIST, Genre.values());
            return "movieForm";
        }

        movieService.save(currentMovie);
        return "redirect:/movies";
    }


}
