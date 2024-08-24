package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < n; i++) {
            list.add(scanner.nextLine());
        }

        for (int i = 0; i < n; i++) System.out.println(list.get(i));

        scanner.close();
    }
}