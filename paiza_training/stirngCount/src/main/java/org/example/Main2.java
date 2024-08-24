package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Nara
//Shiga
//Hokkaido
//Chiba

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        for (int i = 0; i < input.length(); i++) {
            System.out.println(input.charAt(i));
        }
        scanner.close();
    }
}