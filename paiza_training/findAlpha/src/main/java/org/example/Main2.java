package org.example;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        char start = input.charAt(0);
        char end = input.charAt(input.length() - 1);

        if (start == end) {
            System.out.println(start);
        } else {
            for (char c = start; c <= end; c++) {
                System.out.println(c);
            }
        }
    }
}
