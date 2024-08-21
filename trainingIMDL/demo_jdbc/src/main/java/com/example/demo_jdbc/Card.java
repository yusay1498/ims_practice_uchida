package com.example.demo_jdbc;

public record Card(
        Integer id,
        String name,
        Integer level,
        Integer elementId,
        Integer top,
        Integer right,
        Integer bottom,
        Integer left
) {
    public Integer summary() {
        return top + right + bottom + left;
    }
}
