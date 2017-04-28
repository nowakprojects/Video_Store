package pl.nowakprojects.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static pl.nowakprojects.domain.Restriction.*;

/**
 * Created by Mateusz on 22.04.2017.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    @Pattern(regexp = ONLY_LETTERS_REGEX_PATTERN, message = ONLY_LETTERS_MESSAGE)
    private String surname;

    @NotEmpty
    @Column(nullable = false)
    @Pattern(regexp = ONLY_LETTERS_REGEX_PATTERN, message = ONLY_LETTERS_MESSAGE)
    private String name;

    @NotEmpty
    @Column(nullable = false)
    @Email
    private String email;

    @Column
    @Size(min = 9, max = 9)
    @Pattern(regexp = ONLY_NUMBERS_REGEX_PATTERN, message = ONLY_NUMBER_MESSAGE)
    private String phone;

    @NotEmpty
    @Column(nullable = false)
    private String address;

    @NotEmpty
    @Column(nullable = false)
    @Pattern(regexp = ONLY_LETTERS_REGEX_PATTERN, message = ONLY_LETTERS_MESSAGE)
    private String city;


    public Customer(String surname, String name, String email, String phone, String address, String city) {
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
    }
}
