package pl.nowakprojects.database.entity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mateusz on 22.04.2017.
 */
public enum Genre {
    ACTION ("Action"),
    ADULT ("Adult"),
    ADVENTURE ("Adventure"),
    FAMILY ("Family"),
    COMEDY ("Comedy"),
    CRIME ("Crime"),
    DRAMA ("Drama"),
    FANTASY ("Fantasy"),
    HISTORICAL("Historical"),
    HORROR ("Horror"),
    MUSICAL ("Musical"),
    ROMANCE ("Romance"),
    SCIENCE_FICTION ("Science-Fiction"),
    SPY ("Spy"),
    THRILLER ("Thriller"),
    WAR ("War"),
    WESTERN ("Western");

    private final String name;

    Genre(String name) {
        this.name = name;
    }

    private static List<String> getNames(){
        return Arrays.stream(Genre.values()).map(g -> g.name).collect(Collectors.toList());
    }

    private static Genre findByName(String name){
        return Genre.valueOf(name);
    }

    private static Genre findByIndex(int index){
        return Genre.values()[index];
    }
}
