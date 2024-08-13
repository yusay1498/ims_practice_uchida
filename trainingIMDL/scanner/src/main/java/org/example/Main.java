package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Path path = Path.of("data.txt");
        try (Scanner in = new Scanner(path)) {
            while (in.hasNext()) {
                int number = in.nextInt();
                String name = in.next();
                double weight = in.nextDouble();

                System.out.println(number + "\t" + name + "\t" + weight);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            System.out.println(">>" + scanner.next());
        }
    }
}