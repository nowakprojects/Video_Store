package pl.nowakprojects.database.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import pl.nowakprojects.database.Restriction;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Year;

/**
 * Created by Mateusz on 22.04.2017.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(nullable = false)
    @Length(min = 3, max = 128)
    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private String director;

    private String language;

    @Min(Restriction.MOVIE_MIN_RELEASE_YEAR)
    private Integer releaseYear;

    public Movie(String title, Genre genre, String director, String language, Integer releaseYear) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.language = language;
        this.releaseYear = releaseYear;
    }
}
