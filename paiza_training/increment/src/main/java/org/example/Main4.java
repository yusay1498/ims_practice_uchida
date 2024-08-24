package org.example;

import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n < 5) {
            System.out.println("low");
        } else {
            System.out.println("high");
        }
    }
}