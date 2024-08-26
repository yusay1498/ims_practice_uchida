package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int n = 0; n < 3; n++) {
            String input = scanner.nextLine();
            System.out.println(input);
        }
    }
}