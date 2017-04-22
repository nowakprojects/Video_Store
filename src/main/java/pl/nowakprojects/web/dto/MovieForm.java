package pl.nowakprojects.web.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;


/**
 * Created by Mateusz on 22.04.2017.
 */
@Data
public class MovieForm {

    private static final int MIN_RELEASE_YEAR = 1895;

    @NotEmpty
    private String title;

    private String director;

    private String language;

    @Min(MIN_RELEASE_YEAR)
    @Length(min=4,max = 4)
    private String releaseYear;
}
