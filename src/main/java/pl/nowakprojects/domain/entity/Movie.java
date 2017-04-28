package pl.nowakprojects.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static pl.nowakprojects.domain.Restriction.*;

/**
 * Created by Mateusz on 22.04.2017.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(nullable = false)
    @Length(min = 3, max = 128)
    private String title;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Pattern(regexp = ONLY_LETTERS_REGEX_PATTERN, message = ONLY_LETTERS_MESSAGE)
    private String director;

    @Pattern(regexp = ONLY_LETTERS_REGEX_PATTERN, message = ONLY_LETTERS_MESSAGE)
    private String language;

    @NotNull
    @Min(MOVIE_MIN_RELEASE_YEAR)
    @Column(name = "release_year")
    private Integer releaseYear;

    public Movie(String title, Genre genre, String director, String language, Integer releaseYear) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.language = language;
        this.releaseYear = releaseYear;
    }

}

