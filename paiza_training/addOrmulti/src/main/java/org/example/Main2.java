package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<String>();
        list.add(scanner.next());
        list.add(scanner.next());


        for (String item: list) System.out.println(item);

        scanner.close();
    }
}