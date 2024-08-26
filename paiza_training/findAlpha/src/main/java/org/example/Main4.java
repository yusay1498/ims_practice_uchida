package org.example;

import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char start = scanner.next().charAt(0);
        char end = scanner.next().charAt(0);
        char check = scanner.next().charAt(0);

        System.out.println(start <= check && check <= end);
    }
}
