package pl.nowakprojects.domain;

/**
 * Created by Mateusz on 26.04.2017.
 */
public class Restriction {
    public static final int MOVIE_MIN_RELEASE_YEAR = 1895;

    public static final String ONLY_LETTERS_REGEX_PATTERN = "^[a-zA-Z\\s]+$";
    public static final String ONLY_NUMBERS_REGEX_PATTERN = "^[0-9]+$";

    public static final String ONLY_LETTERS_MESSAGE = "only letters allowed.";
    public static final String ONLY_NUMBER_MESSAGE = "only numbers allowed.";
}
