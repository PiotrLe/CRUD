package library.CRUD.model.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

import static java.lang.String.format;
import static java.util.Arrays.stream;

public enum BookStatus {

    ACTIVE("active"),
    CANCELLED("cancelled"),
    ARCHIVED("archived"),
    LOAN("loan"),
    INVALID("invalid"),
    COMPLETED("completed");

    public static final String ALLOWABLE_VALUES = "active, cancelled, archived, loan, invalid,  completed";

    protected String restValue;

    public static BookStatus matchToRestValue(String restValue) {
        return stream(BookStatus.values())
                .filter(value -> Objects.equals(value.restValue, restValue))
                .findAny()
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                format("Any book status could be matched to value '%s'. Expected values: '%s'",
                                        restValue,
                                        ALLOWABLE_VALUES)));
    }

    @JsonValue
    public String getRestValue() {
        return restValue;
    }

    BookStatus(String restValue) {
        this.restValue = restValue;
    }
}
