package pl.nowakprojects.database.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.Year;

/**
 * Created by Mateusz on 22.04.2017.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private static final int MIN_RELEASE_YEAR = 1895;

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private String director;

    private String language;

    @Min(MIN_RELEASE_YEAR)
    @Length(min=4,max = 4)
    private String releaseYear;

    public Movie(String title, Genre genre, String director, String language, String releaseYear) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.language = language;
        this.releaseYear = releaseYear;
    }
}
