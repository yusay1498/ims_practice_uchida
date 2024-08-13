package org.example;

public enum Colors {
    RED("\u001B[41m"),
    BLUE("\u001B[44m"),
    GREEN("\u001B[42m"),
    YELLOW("\u001B[43m"),
    CYAN("\u001B[46m"),
    PURPLE("\u001B[45m"),

    RESET("\u001B[0m"),
    ;

    private String code;

    Colors(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
