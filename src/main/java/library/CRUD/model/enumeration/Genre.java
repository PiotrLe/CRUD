package library.CRUD.model.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

import static java.lang.String.format;
import static java.util.Arrays.stream;

public enum Genre {

    HORROR("horror"),
    THRILLER("thriller"),
    CLASSIC("classic"),
    LOVE_STORY("loveStory"),
    SCIENCE_FICTION("scienceFiction"),
    CRIME_STORY("crimeStory"),
    FANTASY("fantasy"),
    FAIRY_TALE("fairyTale");


    public static final String ALLOWABLE_VALUES = "horror, thriller, classic, loveStory, scienceFiction,  crimeStory, fantasy, fairyTale";

    protected String restValue;

    public static Genre matchToRestValue(String restValue) {
        return stream(Genre.values())
                .filter(value -> Objects.equals(value.restValue, restValue))
                .findAny()
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                format("Any book genre could be matched to value '%s'. Expected values: '%s'",
                                        restValue,
                                        ALLOWABLE_VALUES)));
    }

    @JsonValue
    public String getRestValue() {
        return restValue;
    }

    Genre(String restValue) {
        this.restValue = restValue;
    }
}
