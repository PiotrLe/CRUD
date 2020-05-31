package library.CRUD.model.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

import static java.lang.String.format;
import static java.util.Arrays.stream;

public enum BookStatus {

    ACTIVE("ACTIVE"),
    CANCELLED("CANCELLED"),
    ARCHIVED("ARCHIVED"),
    LOAN("LOAN"),
    INVALID("INVALID"),
    COMPLETED("COMPLETED");

    public static final String ALLOWABLE_VALUES = "ACTIVE, CANCELLED, ARCHIVED, LOAN, INVALID,  COMPLETED";

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
