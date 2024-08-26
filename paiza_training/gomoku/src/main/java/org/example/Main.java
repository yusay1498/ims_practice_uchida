package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 5;
        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            System.out.println(input);
        }
    }
}