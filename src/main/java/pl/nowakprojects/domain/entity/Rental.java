package pl.nowakprojects.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by Mateusz on 22.04.2017.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rental")
public class Rental {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "customer")
    private Customer customer;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "movie")
    private Movie movie;

    @Column(name = "rent_date", updatable = false)
    @CreatedDate
    private LocalDateTime rentDate = LocalDateTime.now();

    private LocalDateTime returnDate;


    public Rental(Customer customer, Movie movie) {
        this.customer = customer;
        this.movie = movie;
    }
}
