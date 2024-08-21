package com.example.demo_jdbc;

public record Card(
        Integer id,
        String name,
        Integer level,
        Integer elementId,
        Integer summary
) {
}
