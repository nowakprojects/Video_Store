package pl.nowakprojects.database.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Mateusz on 22.04.2017.
 */
@Entity
@Data
public class Rental {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Movie movie;

    @Column(nullable = false)
    private Date rentDate;

    private Date returnDate;
}
