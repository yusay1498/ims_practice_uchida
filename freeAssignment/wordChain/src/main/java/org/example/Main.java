package org.example;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Path dictionaryPath = Path.of("resources/dictionary.txt");
        System.out.println(Files.exists(dictionaryPath));
    }
}