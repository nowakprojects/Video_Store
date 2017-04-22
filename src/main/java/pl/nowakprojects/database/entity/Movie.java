package pl.nowakprojects.database.entity;

import lombok.*;

import javax.persistence.*;
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

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private String director;

    private String language;

    private String releaseYear;

    public Movie(String title, Genre genre, String director, String language, String releaseYear) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.language = language;
        this.releaseYear = releaseYear;
    }
}
