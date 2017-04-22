package pl.nowakprojects.rest.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nowakprojects.buisnesslogic.interfaces.MoviesService;
import pl.nowakprojects.database.entity.Genre;
import pl.nowakprojects.database.entity.Movie;

import java.util.List;

/**
 * Created by Mateusz on 22.04.2017.
 */
@RestController
@RequestMapping("/api/movies")
public class MoviesController {

    private final MoviesService moviesService;

    @Autowired
    MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    ResponseEntity<List<Movie>> getAllMovies(){
        List<Movie> allMoviesList = moviesService.getAllMovies();
        HttpStatus httpStatus = allMoviesList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(allMoviesList, httpStatus);
    }

    @RequestMapping(value = "/saveMovie", method = RequestMethod.POST)
    ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        return new ResponseEntity<>(moviesService.saveMovie(movie),HttpStatus.CREATED);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    ResponseEntity<List<Movie>> findMoviesByTitle(
            @RequestParam(required = false) String title){
        return new ResponseEntity<>(moviesService.findByTitle(title),HttpStatus.CREATED);
    }

}
