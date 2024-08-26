package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            String s = scanner.next();
            int k = scanner.nextInt();
            System.out.println(k);
        }
    }
}