package org.example;

import java.util.Scanner;

//Nara
//Shiga
//Hokkaido
//Chiba

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String string = scanner.nextLine();
        int counter = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == input.charAt(0)) {
                counter++;
            }
        }
        System.out.println(counter);
        scanner.close();
    }
}