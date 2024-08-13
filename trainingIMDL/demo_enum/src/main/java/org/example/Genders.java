package org.example;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

public enum Genders {
    MALE("M"),
    FEMALE("F"),
    ;

    private final String code;

    Genders(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Genders of(String code) {
        return Arrays.stream(values())
                .filter(genders -> Objects.equals(genders.getCode(), code))
                .findFirst()
                .orElse(null);
    }
}
