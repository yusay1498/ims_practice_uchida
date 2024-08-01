package org.example;

public class GenericDemo<T> {
    private final T value;

    public GenericDemo(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
