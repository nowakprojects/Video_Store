package pl.nowakprojects.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import pl.nowakprojects.database.entity.Genre;
import pl.nowakprojects.database.entity.Movie;

import javax.validation.constraints.Min;


/**
 * Created by Mateusz on 22.04.2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieForm {

    private static final int MIN_RELEASE_YEAR = 1895;

    private Long id;

    @NotEmpty
    private String title;

    private Genre genre;

    private String director;

    private String language;

    @Min(MIN_RELEASE_YEAR)
    @Length(min=4,max = 4)
    private String releaseYear;


    public MovieForm(Movie movie){
        id=movie.getId();
        title=movie.getTitle();
        genre=movie.getGenre();
        director=movie.getDirector();
        language=movie.getLanguage();
        releaseYear=movie.getReleaseYear();
    }

}
